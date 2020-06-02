package com.jisheng.common.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class CreateJwtTest {
    public static void main(String[] args) {
        Map hashMap = new HashMap();
        hashMap.put("param1","参数1");
        hashMap.put("param2","参数2");
        JwtBuilder jwtBuilder = Jwts.builder().setId("12345")
                .setSubject("主题1").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "qianmingkey")
                .claim("param1","参数1")
                .claim("param2","参数2");
        String compact = jwtBuilder.compact();
        System.out.println(compact);

    }
}
