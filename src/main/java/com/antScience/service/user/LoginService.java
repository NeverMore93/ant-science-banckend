package com.antScience.service.user;

import com.antScience.entity.user.User;
import com.antScience.foundation.annotation.Phone;
import com.antScience.foundation.aspect.ValidateParams;
import com.antScience.foundation.common.TokenService;
import com.antScience.foundation.exception.BusinessException;
import com.antScience.model.user.Identification;
import com.antScience.model.user.LoginCodes;
import com.antScience.model.user.UsersCodes;
import com.antScience.repository.UserRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * Created by lirui on 2017/12/26.
 */
@Service
public class LoginService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private TokenService tokenService;

    @Resource
    private VerificationCodeService verificationCodeService;

    @Resource
    private RegistrationService registrationService;

    @ValidateParams
    public Identification login(@NotEmpty(message = LoginCodes.IdentityIsRequired) String identity, @NotEmpty(message = LoginCodes.PasswordIsRequired) String password) throws Exception {
        User registeredUser = userRepository.findByIdentity(identity);
        if (registeredUser == null) {
            throw new BusinessException(LoginCodes.USER_IS_NOT_EXISTS);
        }
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        User matchedUser = userRepository.findByCredential(identity, encryptedPassword);
        if (matchedUser == null) {
            throw new BusinessException(LoginCodes.USER_OR_PASSWORD_IS_INCORRECT);
        }
        Identification identification = new Identification();
        String token = tokenService.buildToken(matchedUser.getUserName());
        identification.setUserName(matchedUser.getUserName());
        identification.setToken(token);
        return identification;
    }

    @ValidateParams
    public Identification loginByPhone(@NotEmpty(message = UsersCodes.phoneIsRequired) @Phone String phone, @NotEmpty(message = UsersCodes.codeIsRequired) String code) throws Exception {
        verificationCodeService.verify(phone, code);
        User registeredUser = userRepository.findByPhone(phone);
        if (registeredUser == null) {
            registeredUser = registrationService.registerByPhone(phone);
        }
        Identification identification = new Identification();
        String token = tokenService.buildToken(registeredUser.getUserName());
        identification.setUserName(registeredUser.getUserName());
        identification.setToken(token);
        return identification;
    }
}
