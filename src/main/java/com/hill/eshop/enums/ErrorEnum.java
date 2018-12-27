package com.hill.eshop.enums;

public enum ErrorEnum {


    // request参数验证错误码 1XXX
    // UnAuthorized(1000, "token验证错误"),
    LoginFail(1001, "登录出错，请检查账号密码"),
    WrongCaptcha(1002, "验证码错误"),
    NoPermission(1003, "账号无此API权限"),
    ValidationException(1004,"请求参数验证未通过"),
    
    // CRUD錯誤 2XXX
    DataNotFound(2001, "数据未找到"),
    InsertFail(2002, "添加失败"),
    UpdateFail(2003, "更新失败"),
    DeleteFail(2004, "删除失败"),
    ProcedureError(2005, "执行程序失败");

    private Integer code;
    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
