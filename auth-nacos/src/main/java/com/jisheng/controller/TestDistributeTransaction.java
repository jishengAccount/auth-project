package com.jisheng.controller;

import com.jisheng.service.TestFeignService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Transactional
public class TestDistributeTransaction {
    @Autowired
    TestFeignService testFeignService;
    @Autowired
    TestController testController;

    @GlobalTransactional
    @RequestMapping("/testSeata")
    public Object testSeata() throws Exception{
        testFeignService.webSave();//远程调用异常时都不会成功
        testController.nacosSave();//本地nacosSave异常时远程调用webSave会成功所以需要分布式事务
        int i=1/0;
        return null;
    }
}
