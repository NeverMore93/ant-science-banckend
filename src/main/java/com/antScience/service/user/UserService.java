package com.antScience.service.user;

import com.antScience.entity.user.User;
import com.antScience.foundation.annotation.Phone;
import com.antScience.foundation.aspect.ValidateParams;
import com.antScience.model.Registration;
import com.antScience.model.user.UsersCodes;
import com.antScience.repository.UserRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by lirui on 2017/12/24.
 */
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @ValidateParams
    boolean isUserNameExist(@NotEmpty(message = UsersCodes.userNameIsRequired) String userName) {
        User user = userRepository.findByUserName(userName);
        return null != user;
    }

    @ValidateParams
    boolean isPhoneExist(@NotEmpty(message = UsersCodes.phoneIsRequired) @Phone String phone) {
        User user = userRepository.findByPhone(phone);
        return null != user;
    }

    void register(Registration registration) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setPassword(registration.getPassword());
        user.setPhone(registration.getPhone());
        user.setUserName(registration.getUserName());
        userRepository.create(user);
    }

    public User selectByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
