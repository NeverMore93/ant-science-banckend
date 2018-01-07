package com.antScience.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Created by lirui on 2017/12/24.
 */
@Data
public class Registration {

    @Email(message = "邮箱格式错误")
    private String email;

    @NotNull(message = "用户名不可以为空")
    private String userName;

    @NotNull(message = "密码不可以为空")
    private String password;

    private String phone;


}
