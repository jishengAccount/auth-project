package com.jisheng.common.entity.resultCommon;

public enum  ResultCode {
    SUCCESS(true,1000,"登录成功"),
    FAIL(false,1001,"登录失败"),
    UNAUTH(false,1003,"权限不足"),
    UNLOGIN(false,1004,"未登录"),
    SERVER_ERROR(false,1005,"服务器错误"),
    UN_API_AUTH(false,1006,"没有此api权限");

    boolean flag;
    int code;
    String message;
    ResultCode(boolean flag,int code,String message){
        this.flag=flag;
        this.code=code;
        this.message=message;
    }

    public boolean flag(){
        return flag;
    }
    public int code(){
        return code;
    }
    public String message(){
        return message;
    }

}
