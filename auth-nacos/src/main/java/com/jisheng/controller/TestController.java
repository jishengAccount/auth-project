package com.jisheng.controller;


import com.jisheng.entity.User;
import com.jisheng.service.TestFeignService;
import com.jisheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class TestController {
    @Autowired
    TestFeignService testFeignService;
    @Autowired
    TestCustomProperties testCustomProperties;
    @Autowired
    UserService userService;
    @RequestMapping("/test")
    public String test(){
        return  this.testFeignService.test();
    }

    @RequestMapping("/testPro")
    public String testPro(){
        System.out.println(this.testCustomProperties.getStr2());
        return  this.testCustomProperties.getStr2();
    }
    @RequestMapping("/user")
    public Object nacosSave(){
        User user = new User();
        user.setMobile("1221224234423421");
        user.setUsername("baby");
        boolean save = userService.save(user);
        return save;
    }

}
