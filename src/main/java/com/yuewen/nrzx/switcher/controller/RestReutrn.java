package com.yuewen.nrzx.switcher.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * project : switch-server
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestReutrn {
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_SERVER_ERROR = 500;
    private static final String MSG_SUCCESS = "Success";
    private int code;
    private String message;

    private Object result;

    public RestReutrn(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestReutrn(Object result) {
        this.code = CODE_SUCCESS;
        this.message = MSG_SUCCESS;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
