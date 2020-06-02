package com.jisheng.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jisheng.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Component
@RestController
public class RedisCloseOrder extends KeyExpirationEventMessageListener {
    @Autowired
    private UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    public RedisCloseOrder(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }
    @RequestMapping("/setExpireKey")
    public String setExpireKey(){
        redisTemplate.opsForValue().set("11", "哈哈", 10, TimeUnit.SECONDS);

        return "成功";
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String orderNo = message.toString();
        QueryWrapper userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", orderNo);
        userService.remove(userQueryWrapper);
        System.out.println("成功");
    }
}
