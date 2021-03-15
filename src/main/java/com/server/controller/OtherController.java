package com.server.controller;

import com.server.annotation.RemoveUserId;
import com.server.entity.MainInfo;
import com.server.entity.UFile;
import com.server.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "other")
public class OtherController {

    @Autowired
    private OtherService otherService;

    @RemoveUserId
    @RequestMapping(value = "uploadFile")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        return otherService.uploadFile(file);
    }

    @RemoveUserId
    @RequestMapping(value = "deleteFile")
    public Map<String, Object> deleteFile(@RequestParam("uuid") String uuid) {
        return otherService.deleteFile(uuid);
    }

    @RemoveUserId
    @RequestMapping(value = "findAll")
    public Map<String, Object> findAll(@RequestParam("curPage") Integer curPage) {
        return otherService.findAll(curPage);
    }

    @RemoveUserId
    @RequestMapping(value = "findMany")
    public List<UFile> findMany() {
        return otherService.findMany();
    }

    @RemoveUserId
    @RequestMapping(value = "downLoadFile")
    public Map<String,Object>  downLoadFiel(@RequestParam(value = "uuid")String uuid, HttpServletResponse response){
        return otherService.downLoadFile(uuid, response);
    }

    @RemoveUserId
    @RequestMapping(value = "receiveMessage")
    public Map<String, Object> receiveMessage(@RequestBody MainInfo mainInfo){
        return otherService.receiveMessage(mainInfo);
    }

    @RequestMapping(value = "createEamilUser")
    public Map<String, Object> createEamilUser(@RequestParam("email_name") String email_name,
                                               @RequestParam("eamil_password") String eamil_password){
        return otherService.createEamilUser(email_name, eamil_password);
    }

}
