package com.hill.eshop.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseResponse {

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(int code, String msg, String newToken) {
        this.code = code;
        this.msg = msg;
        this.newToken = newToken;
    }

    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String newToken;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNewToken() {
        return newToken;
    }

    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }

}
