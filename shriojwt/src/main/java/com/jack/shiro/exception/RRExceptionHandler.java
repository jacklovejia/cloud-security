package com.jack.shiro.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class RRExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(), e);
        return "没有权限，请联系管理员授权";
    }
}
