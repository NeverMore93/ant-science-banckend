package com.antScience.controller.user;

import com.antScience.entity.user.User;
import com.antScience.foundation.common.Response;
import com.antScience.model.Registration;
import com.antScience.service.user.RegistrationService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lirui on 2017/12/26.
 */
@RestController
public class RegistrationController {

    @Resource
    private RegistrationService registrationService;

    @RequestMapping(value = "/auth/registration", method = RequestMethod.POST)
    public HttpEntity register(@RequestBody Registration registration) throws Exception {
        User registeredUser = registrationService.register(registration);
        return Response.post(registeredUser);
    }

}
