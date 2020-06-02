package com.jisheng.test.controller;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class RabbitMQCloseOrder {
    @Autowired
    AmqpTemplate amqpTemplate;

//    发送
    @RequestMapping("/sendMsg")
    public String sendMsg() {
        amqpTemplate.convertAndSend("ttlExchange", "order.create", "你好");
        return "成功";
    }

//    监听
    @RabbitListener(queues = {"dlQueue"})
    public void listenerMQ(String str,Message msg, Channel channel){
        System.out.println(str);
    }

}
