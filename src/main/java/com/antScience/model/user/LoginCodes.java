package com.antScience.model.user;

import com.antScience.foundation.application.DomainOrder;

/**
 * Created by lirui on 2017/5/17.
 */
public enum LoginCodes {
    USER_IS_NOT_EXISTS("001","账号不存在，请先注册"),
    USER_OR_PASSWORD_IS_INCORRECT("002", "账号或密码错误"),
    ;
    public static final String IdentityIsRequired = "登录失败,请输入您的用户名或邮箱。";
    public static final String PasswordIsRequired = "登录失败,您尚未输入密码。";
    private String code;
    private String message;

    LoginCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return Integer.parseInt(DomainOrder.LOGIN + code);
    }

    public String message() {
        return message;
    }
}
