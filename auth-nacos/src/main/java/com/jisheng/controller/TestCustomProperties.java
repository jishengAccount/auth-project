package com.jisheng.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义参数的读取方式
 */
@Component
@PropertySource({"classpath:custom.properties"})
@ConfigurationProperties("custom") //方法一
@Data
public class TestCustomProperties {
//    @Value("${custom.str1}")   //方法二
    private  String str1;
    private  String str2;

}
