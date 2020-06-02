package com.jisheng.common.interceptor;


import com.jisheng.common.entity.resultCommon.ResultCode;
import com.jisheng.common.exception.CommonException;
import com.jisheng.common.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Auth");
        if (auth!=null&&auth.startsWith("bear")){
            String token=auth.replace("bear","");
            Claims claim = jwtUtils.parse(token);
            if(claim!=null){
                String apis = (String)claim.get("apis");
                //获取方法上面的name权限标识
                HandlerMethod handlerMethod=(HandlerMethod)handler;
                RequestMapping methodAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
                String name = methodAnnotation.name();
                if (apis.contains(name)){
                     request.setAttribute("claim",claim);
                    return true;
                }else{
                    throw new CommonException(ResultCode.UN_API_AUTH);
                }
            }

        }
        throw new CommonException(ResultCode.UNLOGIN);
    }
}
