package com.jisheng.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TestRsa {
    public static PublicKey publicKey;
    public static PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        TestRsa.generatekey();
        TestRsa.generateToken();

    }


    public static void generateToken() throws Exception{
        Map map = new HashMap<>();
        map.put("name", "js");
        String s = JwtUtils.generateToken(map, privateKey, 10000);
        System.out.println(s);
        Map<String, Object> infoFromToken = JwtUtils.getInfoFromToken(s, publicKey);
        System.out.println(infoFromToken);
    }

    public static void parseToken(){

    }



//    生成公钥和私钥
    public static void generatekey() throws Exception {
        RsaUtils.generateKey("G:\\BaiduNetdiskDownload\\谷粒商城\\rsa.pub", "G:\\BaiduNetdiskDownload\\谷粒商城\\rsa.pri", "jisheng");
        privateKey = RsaUtils.getPrivateKey("G:\\BaiduNetdiskDownload\\谷粒商城\\rsa.pri");
        publicKey = RsaUtils.getPublicKey("G:\\BaiduNetdiskDownload\\谷粒商城\\rsa.pub");
        System.out.println("private:" + privateKey);
        System.out.println("public:" + publicKey);
    }


}
