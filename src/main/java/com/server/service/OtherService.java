package com.server.service;

import com.server.entity.UFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface OtherService {

    Map<String, Object> uploadFile(MultipartFile file);

    Map<String, Object> deleteFile(String uuid);

    Map<String, Object> findAll(Integer curPage);

    List<UFile> findMany();

    Map<String, Object> downLoadFile(String uuid, HttpServletResponse response);


    Map<String, Object> receiveMessage();
}
