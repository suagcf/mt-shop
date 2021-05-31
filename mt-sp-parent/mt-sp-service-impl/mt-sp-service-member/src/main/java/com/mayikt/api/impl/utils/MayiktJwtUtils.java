package com.mayikt.api.impl.utils;

import com.mayikt.api.impl.entity.UserInfoDo;
import com.mayikt.api.utils.DesensitizationUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


/**
 * mayikt jwt
 */
public class MayiktJwtUtils {


    private static final String SUBJECT = "mayikt";
    private static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;
    private static final String APPSECRET_KEY = "mayikt_secret";

    public static String generateJsonWebToken(UserInfoDo userInfoDo) {
        String token = Jwts
                .builder()
                .setSubject(SUBJECT)
                .claim("userId", userInfoDo.getUserId())
                .claim("age",userInfoDo.getAge())
                .claim("mobile", DesensitizationUtil.mobileEncrypt(userInfoDo.getMobile()))
                .setIssuedAt(new Date())
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }


    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取手机
     *
     * @param token
     * @return
     */
    public static String getMobile(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("mobile").toString();
    }


    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

}