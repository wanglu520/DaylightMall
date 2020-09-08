package com.example.service.Utils;

import java.util.Date;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Wang
 * @create: 2020-08-24
 */
@Service("jwtUtils")
@Setter
@Getter
@ConfigurationProperties("jwt.config")
public class JwtUtils {
    //签名私钥改
    private String key;
    //签名失效时间
    private Long failureTime;
    Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 生成token
     *
     * @param userId
     * @param username
     * @return
     */
    public String getToken(String userId, String username) {
        try {
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
        } catch (Exception e) {
            logger.error("Token生成失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.error("token解析失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        Claims claims = parseJwt(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    /**
     * 获取用户id
     *
     * @param token
     * @return
     */
    public String getUserId(String token) {
        Claims claims = parseJwt(token);
        if (claims != null) {
            return claims.getId();
        }
        return null;
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public boolean isExpiration(String token) {
        Claims claims = parseJwt(token);
        if (claims != null) {
            return claims.getExpiration().before(new Date());
        }
        return true;
    }

    public boolean verify(String token) {
        return StringUtils.isEmpty(getUserId(token)) || isExpiration(token);
    }
}
