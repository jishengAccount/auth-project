package com.jisheng.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用threadLocal进行传参
 */

public class MyInterceptor extends HandlerInterceptorAdapter {
    private  static ThreadLocal threadLocal=new ThreadLocal();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        threadLocal.set("传给controller的参数");

        return super.preHandle(request, response, handler);
    }

    //调用完成进行销毁，因为使用的是tomcat线程池，所以线程不会被销毁，需要手动清除掉threadlocal中的数据
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        threadLocal.remove();

        super.afterCompletion(request, response, handler, ex);
    }

    //后续controller可以通过MyInterceptor.getParam获取参数
    public static Object getParam(){

        return threadLocal.get();
    }

}
