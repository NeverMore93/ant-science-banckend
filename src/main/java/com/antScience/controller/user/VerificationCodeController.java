package com.antScience.controller.user;

import com.antScience.foundation.common.Response;
import com.antScience.model.user.VerificationCode;
import com.antScience.service.user.VerificationCodeService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by lirui on 2017/12/27.
 */
@RestController
public class VerificationCodeController {

    @Resource
    private VerificationCodeService verificationCodeService;

    @RequestMapping(value = "/auth/verificationCode/phone/{phone}", method = RequestMethod.GET)
    public HttpEntity getVerificationCode(@PathVariable("phone") String phone) throws IOException {
        VerificationCode verificationCode = verificationCodeService.getByPhone(phone);
        return Response.build(verificationCode);
    }

    @RequestMapping(value = "/auth/verificationCode", method = RequestMethod.GET)
    public HttpEntity verifyCode(@RequestParam("phone") String phone, @RequestParam("code") String code) throws IOException {
        VerificationCode verificationCode = verificationCodeService.verify(phone, code);
        return Response.build(verificationCode);
    }
}
