package com.jisheng.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列配置
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange ttlExchange() {
        TopicExchange ttlExchange = new TopicExchange("ttlExchange");
        return ttlExchange;
    }
    @Bean
    public TopicExchange dlExchange() {
        TopicExchange dlExchange = new TopicExchange("dlExchange");
        return dlExchange;
    }

    //1,声明一个TTL队列（设置过期时将要转发到的死信exchange和routekey）
    @Bean("ttlQueue")
    public Queue ttlQueue() {
        Map arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "dlExchange");
        arguments.put("x-dead-letter-routing-key", "order.close");
        arguments.put("x-message-ttl", 10000);
        Queue ttlQueue = new Queue("ttlQueue", true, false, false, arguments);
        return ttlQueue;
    }


    //2,将TTL队列绑定到exchange上
    @Bean
    public Binding bindTTLQUEUE() {
       return new Binding("ttlQueue", Binding.DestinationType.QUEUE, "ttlExchange", "order.create", null);
    }

    //3,声明一个死信队列
    @Bean("dlQueue")
    public Queue dlQueue() {

        return new Queue(
                "dlQueue", true, false, false, null);
    }
    //4,将死信队列绑定到exchange上
    @Bean
    public Binding bindDLQUEUE(){
       return  new Binding("dlQueue", Binding.DestinationType.QUEUE,"dlExchange","order.close",null);
    }

}
