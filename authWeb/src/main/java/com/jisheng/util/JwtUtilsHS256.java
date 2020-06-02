package com.jisheng.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Getter
@Setter
@ConfigurationProperties("jwt.config")
public class JwtUtilsHS256 {
    //不能为static
    private  String signKey;//签名key
    private  long expirDate;//过期时间
    public String CreateClaims(Map map,String signKey,long expirDate){
        long l = System.currentTimeMillis() + expirDate;
        Date date = new Date(l);
        JwtBuilder jwtBuilder = Jwts.builder().setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, signKey);
        jwtBuilder.setExpiration(date);
        jwtBuilder.setClaims(map);
        String compact = jwtBuilder.compact();
        return compact;
    }

    public  Map<String,Object> parse(String jwtStr,String signKey){
        Map<String,Object> body = Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwtStr).getBody();

        return body;
    }

}
