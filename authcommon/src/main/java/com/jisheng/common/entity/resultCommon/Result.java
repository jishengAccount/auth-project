package com.jisheng.common.entity.resultCommon;

import lombok.Data;

@Data
public class Result {
    private boolean success;
    private int code;
    private String message;
    private Object data;

    public Result(ResultCode resultCode){
        this.success=resultCode.flag;
        this.code=resultCode.code;
        this.message=resultCode.message;
    }

    public Result(ResultCode resultCode,Object data){
        this.success=resultCode.flag;
        this.code=resultCode.code;
        this.message=resultCode.message;
        this.data=data;
    }

}
