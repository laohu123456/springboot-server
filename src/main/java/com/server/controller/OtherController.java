package com.server.controller;

import com.server.annotation.RemoveUserId;
import com.server.annotation.comoutingtime.CountTime;
import com.server.annotation.test.GetPojo;
import com.server.entity.MainInfo;
import com.server.entity.Poi;
import com.server.entity.UFile;
import com.server.entity.User;
import com.server.redis.jedisserver.service.JedisStringService;
import com.server.redis.jedisserver.service.JedisSystemService;
import com.server.service.OtherService;
import com.server.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CountTime
@RestController
@RequestMapping(value = "other")
public class OtherController {

    @Autowired
    private OtherService otherService;

    @Autowired
    private PoiService poiService;

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


    @Autowired
    private JedisSystemService jedisSystemService;

    @Autowired
    private JedisStringService jedisStringService;

    @RequestMapping(value = "createExcel")
    public void createExcel() throws IOException {
        poiService.writeExcel();
        //jedisSystemService.scankey();
        //String[] msetArray = {"d1","a1","e1","f1"};
        //jedisStringService.addArray(msetArray);
       // String[] msetArray1 = {"d1","e1"};
       // List<String> values = jedisStringService.getValues(msetArray1);
       // System.out.println(values);
    }

    @GetPojo(className = User.class)
   // @NeedArgsHandler(requestNeed = true, responseNeed = true)
    @RequestMapping(value = "testHandler")
    public Map<String, Object> testHandler(@RequestBody(required = false) User user) {
       // System.out.println(user);
        Map<String, Object> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        return map;
    }


    @RequestMapping(value = "producer")
    public Map<String, Object> producer(@RequestParam("offestNum") Integer offestNum){
        Map<String, Object> map = new HashMap<>();
        List<Poi> producer = poiService.producer(offestNum);
        map.put("data", producer);
        return map;
    }

}
