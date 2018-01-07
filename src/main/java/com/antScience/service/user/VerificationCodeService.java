package com.antScience.service.user;

import com.antScience.foundation.aspect.ValidateParams;
import com.antScience.foundation.common.redis.RedisUtils;
import com.antScience.foundation.common.submail.MessageUtil;
import com.antScience.foundation.exception.BusinessException;
import com.antScience.model.user.UsersCodes;
import com.antScience.model.user.VerificationCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by lirui on 2017/12/27.
 */
@Service
public class VerificationCodeService {

    @Resource
    private RedisUtils redisUtils;

    @ValidateParams
    public VerificationCode getByPhone(@NotEmpty(message = UsersCodes.phoneIsRequired) String phone) throws IOException {
        String code = MessageUtil.send(phone);
        redisUtils.setForCode(phone, code);
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setPhone(phone);
        return verificationCode;
    }

    @ValidateParams
    public VerificationCode verify(@NotEmpty(message = UsersCodes.phoneIsRequired) String phone, @NotEmpty(message = UsersCodes.codeIsRequired)String code) {
        String cachedCode = String.valueOf(redisUtils.get(phone));
        if (!code.equals(cachedCode)) {
            throw new BusinessException(UsersCodes.VERIFICATION_CODE_IS_INCORRECT);
        }
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setPhone(phone);
        return verificationCode;
    }
}
