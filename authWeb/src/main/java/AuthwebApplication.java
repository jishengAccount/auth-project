package com.jisheng.login;

import com.jisheng.common.jwt.JwtUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.jisheng","mybatisPlus"})
//@EntityScan(value="com.jisheng.common.entity")
@EnableDiscoveryClient
@MapperScan({"com.jisheng.*","mybatisPlus"})
//componentScan配过一次后第二次不用配置为什么
//@ComponentScan(basePackages ={"com.jisheng.login.dao","com.jisheng.login.controller","com.jisheng.login.service"})
public class AuthwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthwebApplication.class, args);
	}

	@Bean
	public JwtUtils getJwtUtils(){return  new JwtUtils();}
}
