package com.jisheng.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TestHS256 {
    public static PublicKey publicKey;
    public static PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        TestHS256.generateToken();

    }


    public static void generateToken() throws Exception{
        Map map = new HashMap<>();
        map.put("name", "js");
        //生成token
        String token = new JwtUtilsHS256().CreateClaims(map, "jisheng", 10000);
        System.out.println(token);
        //解析token
        Map<String, Object> parse = new JwtUtilsHS256().parse(token,"jisheng");
        System.out.println(parse);
    }





}
