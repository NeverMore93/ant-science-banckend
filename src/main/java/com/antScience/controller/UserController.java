package com.antScience.controller;

import com.antScience.entity.user.User;
import com.antScience.foundation.common.Response;
import com.antScience.service.user.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lirui on 2017/12/24.
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/userName/{userName}", method = RequestMethod.GET)
    public HttpEntity selectByUserName(@PathVariable("userName") String userName){
        User user = userService.selectByUserName(userName);
        return Response.build(user);
    }

}
