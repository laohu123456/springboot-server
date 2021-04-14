package com.server.controller;

import com.server.annotation.comoutingtime.CountTime;
import com.server.dao.UserMapper;
import com.server.entity.ExceptionEntity;
import com.server.entity.User;
import com.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;


    @CountTime
    @RequestMapping(value = "login")
    public Map<String, Object> userLogin(@RequestParam(value = "user_name") String user_name,
                                         @RequestParam(value = "user_password") String user_password) {
        return userService.findUserByNameAndPasswd(user_name, user_password);
    }

    @RequestMapping(value = "oauthUser")
    public ExceptionEntity oauthUser(@RequestParam(value = "titck") String titck){
        return userService.oauthUser(titck);
    }

    @RequestMapping(value = "rejisterUser")
    public Map<String, Object> rejisterUser(@RequestParam("register_name") String register_name,
                                            @RequestParam("register_password") String register_password,
                                            @RequestParam("register_email") String register_email){
        return userService.rejisterUser(register_name, register_password, register_email);
    }

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @RequestMapping("/test")
    public void aaa() throws SQLException {
        User user = new User();
        user.setEmail("test@hyq.a");
        user.setUserName("test");
        user.setPassWord("test");
        user.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        userMapper.insertUser(user);
        System.out.println(dataSource.getConnection().getTypeMap().toString());
        User user1 = userMapper.findUserByName("test");
        System.out.println(dataSource.getConnection().getTypeMap().toString());
    }

}
