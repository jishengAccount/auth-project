package com.jisheng.common.exception;

import com.jisheng.common.entity.resultCommon.ResultCode;
import lombok.Getter;

@Getter
public class CommonException extends Exception {
    public ResultCode resultCode;

    public CommonException(ResultCode resultCode){
        this.resultCode=resultCode;
    }

}
