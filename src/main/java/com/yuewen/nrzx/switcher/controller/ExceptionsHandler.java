package com.yuewen.nrzx.switcher.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    private static final RestReutrn REST_REUTRN_SERVER_ERROR =
        new RestReutrn(RestReutrn.CODE_SERVER_ERROR, "Server Internal Error");

    @ExceptionHandler(Exception.class)
    public RestReutrn allExceptionHandler(Exception exception) {
        return REST_REUTRN_SERVER_ERROR;
    }
}
