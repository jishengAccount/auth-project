package com.jisheng.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseJwtTest {
    public static void main(String[] args) {
        String key="qianmingkey";
        String claimsJwts="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NSIsInN1YiI6IuS4u-mimDEiLCJpYXQiOjE1ODQ0OTUyMTgsInBhcmFtMSI6IuWPguaVsDEiLCJwYXJhbTIiOiLlj4LmlbAyIn0.jKZ8RUoK40jCgsmOKdN4ohdCDvzyiA8RiwSbK82pTn8";
        Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(claimsJwts).getBody();
        System.out.println("id:"+body.getId()+"---subject:"+body.getSubject()+"---签名时间:"+body.getIssuedAt());
        System.out.println("param:"+body.get("param1")+body.get("param2"));
    }
}
