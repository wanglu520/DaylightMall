package com.example.service.Utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mr.Wang
 * @create: 2020-08-24
 */
@Setter
@Getter
@ConfigurationProperties("jwt.config")
public class JwtUtils {
    //签名私钥改
    private String key;
    //签名失效时间
    private Long failureTime;

    /**
     * 生成token
     * @param userId
     * @param username
     * @return
     */
    public String getToken(String userId, String username){
        //失效时间
        Long exp = System.currentTimeMillis() + failureTime;
        //创建JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(userId).setSubject(username)
                .setIssuedAt(new Date())
                //设置签名防止篡改
                .signWith(SignatureAlgorithm.HS256, key);
        jwtBuilder.setExpiration(new Date(exp));
        //创建token
        return jwtBuilder.compact();
    }

    /**
    * 解析token
    *
    * @param token
    * @return
    */
    public Claims parseJwt(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

     }
}
