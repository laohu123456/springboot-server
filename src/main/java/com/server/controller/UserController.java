package com.server.controller;

import com.server.entity.ExceptionEntity;
import com.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;


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

}
