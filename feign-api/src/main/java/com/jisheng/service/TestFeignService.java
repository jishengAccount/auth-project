package com.jisheng.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("auth-web")
public interface TestFeignService {
    @RequestMapping("/test")
    public String test();

    @RequestMapping(value = "/webSave",method = RequestMethod.GET)
    public Object webSave() throws Exception;
}
