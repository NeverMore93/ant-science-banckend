package com.antScience.service.user;

import com.antScience.entity.user.User;
import com.antScience.foundation.exception.BusinessException;
import com.antScience.model.Registration;
import com.antScience.model.user.UsersCodes;
import com.antScience.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * Created by lirui on 2017/12/24.
 */
@Service
public class RegistrationService {

    @Resource
    private UserService userService;

    @Resource
    private UserRepository userRepository;

    public User register(Registration registration) {
        boolean isUserNameExist = userService.isUserNameExist(registration.getUserName());
        if (isUserNameExist) {
            throw new BusinessException(UsersCodes.USERNAME_IS_ALREADY_EXISTS);
        }
        boolean isPhoneExist = userService.isPhoneExist(registration.getPhone());
        if (isPhoneExist) {
            throw new BusinessException(UsersCodes.PHONE_IS_ALREADY_EXISTS);
        }
        String password = DigestUtils.md5DigestAsHex(registration.getPassword().getBytes());
        registration.setPassword(password);
        userService.register(registration);
        return userRepository.findByUserName(registration.getUserName());
    }

    public User registerByPhone(String phone) {
        Registration registration = new Registration();
        registration.setUserName(phone);
        registration.setPhone(phone);
        userService.register(registration);
        return userService.selectByUserName(registration.getUserName());
    }
}
