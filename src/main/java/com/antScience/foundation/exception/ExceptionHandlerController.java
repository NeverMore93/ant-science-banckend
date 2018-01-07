package com.antScience.foundation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lirui on 2017/5/9.
 */
@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(value = InvalidParamsException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> illegalArgumentException(InvalidParamsException e) {
        return createResponseEntity(e);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getBusinessException(BusinessException e) {
        return createResponseEntity(e);
    }

    private ResponseEntity<Map<String, Object>> createResponseEntity(BusinessException e) {
        int code = e.getCode();
        String message = e.getMessage();
        Map<String, Object> body = new HashMap<>();
        body.put("code", code);
        body.put("data", message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
