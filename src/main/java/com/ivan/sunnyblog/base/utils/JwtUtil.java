package com.ivan.sunnyblog.base.utils;

import com.ivan.sunnyblog.base.models.tables.pojos.BlogUser;
import com.ivan.sunnyblog.web.vo.UserInfoVo;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: jinghaoliang
 **/

public class JwtUtil {

    private static final String SECRET_KEY = "ljh123456789!@ljh";

    public static String createToken (BlogUser blogUser) {
        Map<String, Object> claims = new HashMap<>();
//        UserInfoVo userInfoVo = new UserInfoVo();
//        userInfoVo.setUserId(12l);
        claims.put("userId", blogUser.getUserId());
//        claims.put("avatar", blogUser.getAvatar());
//        claims.put("email", blogUser.getEmail());
//        claims.put("nickname", blogUser.getNickname());
        String token = Jwts.builder().setClaims(claims).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return token;
    }

    public static Map<String, Object> verifyToken(String token){
        try{
            Jwt parse = Jwts.parser().setSigningKey(SECRET_KEY).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static Map<String, Object> verifyToken(String token){
//        try{
//            Jwt parse = Jwts.parser().setSigningKey(SECRET_KEY).parse(token);
//            return (Map<String, Object>) parse.getBody();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    public static Map<String, Object> verifyUserToken(String token){
//        try{
//            Jwt parse = Jwts.parser().setSigningKey(SECRET_KEY).parse(token);
//            Map<String, Object> userInfoMap = (Map<String, Object>) parse.getBody();
//            UserInfoVo userInfoVo = new UserInfoVo();
//            return (Map<String, Object>) parse.getBody();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
