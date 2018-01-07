package com.antScience.controller.user;

import com.antScience.foundation.common.Response;
import com.antScience.model.user.Identification;
import com.antScience.service.user.LoginService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lirui on 2017/12/26.
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public HttpEntity login(@RequestParam(required = false) String identity, @RequestParam(required = false) String password) throws Exception {
        Identification identification = loginService.login(identity, password);
        return Response.build(identification);
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public HttpEntity loginByPhone(@RequestParam("phone") String phone, @RequestParam("code") String code) throws Exception {
        Identification identification = loginService.loginByPhone(phone, code);
        return Response.build(identification);
    }
}
