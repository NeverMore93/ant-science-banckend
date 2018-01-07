package com.antScience.foundation.common;

import com.antScience.foundation.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by lirui on 2017/5/16.
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone phone) {
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "^1\\d{10}$";
        return str == null || str.matches(regex);
    }

}
