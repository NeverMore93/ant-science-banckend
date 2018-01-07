package com.antScience.model.user;


import com.antScience.foundation.application.DomainOrder;

/**
 * Created by xubt on 9/14/16.
 */
public enum UsersCodes {
    VERIFICATION_CODE_IS_EXPIRED("001", "验证码已过期"),
    VERIFICATION_CODE_IS_INCORRECT("002", "验证码错误"),
    USERNAME_IS_ALREADY_EXISTS("003", "用户名已经被使用,请使用其他用户名。"),
    PHONE_IS_ALREADY_EXISTS("004", "手机已经存在,请使用其他手机。"),
    EMAIL_IS_ALREADY_EXISTS("005", "邮箱已经存在,请使用其他邮箱。"),
    AVATAR_IS_EMPTY("006", "请上传头像文件。"),
    AVATAR_IS_OUT_OF_MAX_SIZE("007", "头像文件已经超过100KB限制。");
    public static final String identityIsRequired = "请提供用户标识:用户名或者密码。";
    public static final String phoneIsRequired = "手机不可以为空。";
    public static final String codeIsRequired = "验证码不可以为空。";
    public static final String userNameIsRequired = "用户名不可以为空。";
    public static final String md5PasswordIsRequired = "MD5密码不可以为空。"
            ;
    private String code;
    private String message;

    UsersCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return Integer.parseInt(DomainOrder.REGISTRATION + "" + code);
    }

    public String message() {
        return message;
    }
}
