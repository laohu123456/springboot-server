package com.server.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.config.CommonUtils;
import com.server.config.LocalSession;
import com.server.dao.UFileMapper;
import com.server.entity.HttpResponseEntity;
import com.server.entity.MainInfo;
import com.server.entity.UFile;
import com.server.service.OtherService;
import com.server.utils.DateUtils;
import com.server.utils.FtpUtils;
import com.server.utils.HttpClientUtils;
import com.server.utils.OtherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OtherServiceImpl implements OtherService {

    @Autowired
    private UFileMapper uFileMapper;

    @Autowired
    private CommonUtils commonUtils;

    @Transactional
    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            String filename = file.getOriginalFilename();
            String uuid = OtherUtils.getUUID();
            String realName = FtpUtils.createRealFileName(filename, uuid);
            String user_id = LocalSession.getUserId();
            boolean ifSuccess = FtpUtils.uploadFileMethod(file.getInputStream(), realName);
            if (ifSuccess) {
                UFile uFile = new UFile();
                uFile.setId(uuid);
                uFile.setFileName(filename);
                uFile.setRealName(realName);
                uFile.setUserId(user_id);
                uFile.setCreateTime(DateUtils.getCurrentDate());
                int i = uFileMapper.insertUploadFile(uFile);
                map.put("code", "200");
                map.put("uuid", uuid);
                map.put("realName", realName);
            } else {
                map.put("code", "500");
            }
        } catch (IOException e) {
            map.put("code", 500);
            map.put("message", "上传文件失败,系统异常");
            e.printStackTrace();
        }
        return map;
    }


    @Transactional
    @Override
    public Map<String, Object> deleteFile(String uuid) {
        Map<String, Object> map = new HashMap<>();
        try {
            String user_id = LocalSession.getUserId();
            UFile uFile = uFileMapper.findFileById(uuid);
            if (uFile == null) {
                map.put("code", 500);
                map.put("message", "文件不存在");
                return map;
            }
            boolean equalUserId = commonUtils.equalUser_id(user_id, uFile.getUserId());
            if (!equalUserId) {
                map.put("code", 500);
                map.put("message", "您不能操作其他人员文件");
                return map;
            }
            boolean success = FtpUtils.deleteFileMethod(uFile.getRealName());
            if (success) {
                uFileMapper.deleteFileById(uuid);
                map.put("code", 200);
            } else {
                map.put("code", 500);
                map.put("message", "删除文件失败");
            }
        } catch (Exception e) {
            map.put("code", 500);
            map.put("message", "删除文件失败,系统异常");
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> findAll(Integer curPage) {
        Map<String, Object> map = new HashMap<String, Object>();
        String user_id = LocalSession.getUserId();
        List<UFile> list = uFileMapper.findFileByUserId(user_id, (curPage - 1) * 10, 10);
        int allSize = uFileMapper.findAllSize(user_id);
        map.put("data", list);
        map.put("total", allSize);
        map.put("pageSize", 10);
        return map;
    }

    @Override
    public List<UFile> findMany() {
        String user_id = LocalSession.getUserId();
        return uFileMapper.findAllFileByUserId(user_id);
    }

    @Override
    public Map<String, Object> downLoadFile(String uuid, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            String user_id = LocalSession.getUserId();
            UFile uFile = uFileMapper.findFileById(uuid);
            if (uFile == null) {
                map.put("code", 500);
                map.put("message", "文件不存在");
                return map;
            }
            boolean equalUserId = commonUtils.equalUser_id(user_id, uFile.getUserId());
            if (!equalUserId) {
                map.put("code", 500);
                map.put("message", "您不能操作其他人员文件");
                return map;
            }
            boolean success = FtpUtils.downLoadFileMethod(uFile.getFileName(), uFile.getRealName(), response);
            System.out.println(success);
            if (success) {
                map.put("code", 200);
            } else {
                map.put("code", 500);
                map.put("message", "下载文件失败");
            }
        } catch (Exception e) {
            map.put("code", 500);
            map.put("message", "下载文件失败,系统异常");
            e.printStackTrace();
        }
        return map;
    }


    @Override
    public Map<String, Object> receiveMessage(MainInfo mainInfo) {
        Map<String, Object> map = new HashMap<>();
        try{
            //String message, String host, Integer port, String uri
            ObjectMapper objectMapper = new ObjectMapper();
            String mainInfoMessage = objectMapper.writeValueAsString(mainInfo);
            HttpResponseEntity httpResponseEntity = HttpClientUtils.post_init("localhost", 8091, "/provider/receiveMessage", "json", mainInfoMessage);
            map.put("code", httpResponseEntity.getCode());
            map.put("message", httpResponseEntity.getDate());
        }catch (Exception e){
            map.put("code", 500);
            map.put("message", "系统异常");
            e.printStackTrace();
        }
        return map;
    }
}
