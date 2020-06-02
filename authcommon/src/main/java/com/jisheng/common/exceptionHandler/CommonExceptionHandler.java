package com.jisheng.common.exceptionHandler;

import com.jisheng.common.entity.resultCommon.Result;
import com.jisheng.common.entity.resultCommon.ResultCode;
import com.jisheng.common.exception.CommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response,Exception e){
        e.printStackTrace();
        if (e.getClass()== CommonException.class){
            CommonException e1 = (CommonException) e;
            return new Result(e1.getResultCode());
        }
        return new Result(ResultCode.SERVER_ERROR);
    }
}
