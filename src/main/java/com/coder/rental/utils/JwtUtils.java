package com.coder.rental.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtils {
    //JWT密钥
    public static final String SECRET_KEY = "teacher_shi";
    //过期时间
    public static final long EXPIRE_TIME = 1000L*60*60*24;
    /**
     * 创建一个JWT Token。
     * @param payload 包含Token有效载荷的Map，将被添加签发时间、过期时间和生效时间。
     * @return 生成的JWT Token字符串。
     */
    public static String createToken(Map<String, Object> payload){
        DateTime now= DateTime.now();
        DateTime newTime=new DateTime(now.getTime()+EXPIRE_TIME);
// 设置Token的签发时间
        payload.put(JWTPayload.ISSUED_AT,now);
// 设置Token的过期时间
        payload.put(JWTPayload.EXPIRES_AT,newTime);
// 设置Token的生效时间，确保Token在签发后立即生效
        payload.put(JWTPayload.NOT_BEFORE,now);
        return JWTUtil.createToken(payload,SECRET_KEY.getBytes());
    }
    /**
     * 解析JWT Token并返回其Payload。
     *
     * @param token 待解析的JWT Token字符串。
     * @return 解析出的JWT Payload对象。
     * @throws RuntimeException 如果Token验证失败或已过期，则抛出异常。
     */
    public static JWTPayload parseToken(String token){
        JWT jwt = JWTUtil.parseToken(token); // 解析传入的token字符串
        if (!jwt.setKey(SECRET_KEY.getBytes()).verify()){ // 验证token的签名，确保token未被篡改
            throw new RuntimeException("token异常");
        }
        if (!jwt.validate(0)){ // 检查token是否过期
            throw new RuntimeException("token已过期");
        }
        return jwt.getPayload(); // 返回token的payload部分
    }
    /* public static void main(String[] args) {
     *//*Map<String, Object> payload = new HashMap<>();
            payload.put("username","admin");
            payload.put("id","111");
            String token = createToken(payload);
            System.out.println(token);*//*
            String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE3MTA5ODQxMDIsImlkIjoiMTExIiwiZXhwIjoxNzEwOTg1OTAyLCJpYXQiOjE3MTA5ODQxMDIsInVzZXJuYW1lIjoiYWRtaW4ifQ.zsxm_nzfPa-hDDcnMa_NGTxwWqhZAyK26xZbOlXW8IA";
            JWTPayload payload = parseToken(token);
            System.out.println(payload.getClaim("username"));
            System.out.println(payload.getClaim("id"));
            NumberWithFormat claim = (NumberWithFormat)payload.getClaim(JWTPayload.EXPIRES_AT);
            DateTime convert =(DateTime) claim.convert(DateTime.class, claim);
            long expireTime = convert.getTime();
            long nowTime = DateTime.now().getTime();
            System.out.println((expireTime-nowTime)/1000);
    }*/
}
