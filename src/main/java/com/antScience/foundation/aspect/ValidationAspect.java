package com.antScience.foundation.aspect;

import com.antScience.foundation.exception.InvalidParamsException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by lirui on 2017/5/10.
 */
@Component
@Aspect
public class ValidationAspect {

    @Before("execution(* *(@org.springframework.web.bind.annotation.RequestBody *,..))||execution(* *(@org.springframework.web.bind.annotation.RequestBody (*),..,..))")
    public void validateBodyParams(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Set<ConstraintViolation<Object>> constraintViolations = getValidator().validate(arg);
            if (constraintViolations.size() > 0) {
                throw new InvalidParamsException(constraintViolations.iterator().next().getMessage());
            }
        }
    }

    private Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @Before("@annotation(com.antScience.foundation.aspect.ValidateParams)")
    public void validateQueryParams(JoinPoint joinPoint) throws Throwable {
        System.out.println("执行了注解validateParams");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] parameterValues = joinPoint.getArgs();
        Set<ConstraintViolation<Object>> violations = getMethodValidator().validateParameters(joinPoint.getTarget(), method, parameterValues);
        if (!violations.isEmpty()) {
            throw new InvalidParamsException(violations.iterator().next().getMessage());
        }
    }

    private ExecutableValidator getMethodValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator().forExecutables();
    }

}
