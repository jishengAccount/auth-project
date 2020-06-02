package com.jisheng.common.jwt;

import io.jsonwebtoken.Claims;
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
public class JwtUtils {
    //不能为static
    private  String signKey;//签名key
    private  long expirDate;//过期时间
    public String CreateClaims(String id, String name, Map map){
        long l = System.currentTimeMillis() + expirDate;
        Date date = new Date(l);
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, signKey);
        jwtBuilder.setExpiration(date);
        jwtBuilder.setClaims(map);
        String compact = jwtBuilder.compact();
        return compact;
    }

    public  Claims parse(String jwtStr){
        Claims body = Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwtStr).getBody();

        return body;
    }

}
