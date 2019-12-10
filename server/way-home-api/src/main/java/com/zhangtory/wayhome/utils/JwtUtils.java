package com.zhangtory.wayhome.utils;

import com.zhangtory.wayhome.constant.ExceptionConstant;
import com.zhangtory.wayhome.entity.User;
import com.zhangtory.wayhome.exception.UserException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * @author ZhangYaoYu
 * @date 2019/12/10 21:23
 */
public class JwtUtils {

    public static final String JWT_ISSUER = "WayHome_Server_0.1";

    public static final Long DEFAULT_EXPIRATION = 7 * 24 * 60 * 60 * 1000L;

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createToken(User user) {
        try {
            return Jwts.builder()
                    .signWith(KEY)
                    .setIssuer(JWT_ISSUER)
                    .setIssuedAt(new Date())
                    .setId(user.getId().toString())
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + DEFAULT_EXPIRATION))
                    .compact();
        } catch (ExpiredJwtException e) {
            // token过期
            throw new UserException(ExceptionConstant.TOKEN_EXPIRED);
        } catch (Exception e) {
            // token无效
            throw new UserException(ExceptionConstant.TOKEN_INVALID);
        }
    }

    public static Claims getTokenBody(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    public static String getId(String token) {
        return getTokenBody(token).getId();
    }

    public static String getSubject(String token) {
        return getTokenBody(token).getSubject();
    }


}