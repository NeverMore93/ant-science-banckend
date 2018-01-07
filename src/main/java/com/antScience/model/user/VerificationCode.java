package com.antScience.model.user;

import lombok.Data;

import java.util.Date;

/**
 * Created by lirui on 2017/12/27.
 */
@Data
public class VerificationCode {
    private String code;
    private String phone;
    private Date date;
}
