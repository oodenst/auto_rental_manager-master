package com.coder.rental.security;//package com.coder.rental.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.coder.rental.entity.User;
import com.coder.rental.utils.JwtUtils;
import com.coder.rental.utils.RedisUtils;
import com.coder.rental.utils.ResultCode;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
public class LoginSucdessHandler implements AuthenticationSuccessHandler {
    @Resource
    private RedisUtils redisUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //设置客户端的响应的内容类型是json
        response.setContentType("application/json;charset=utf-8");
        User user = (User)authentication.getPrincipal();
        //生成token的处理
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getId());
        String token = JwtUtils.createToken(map);
        NumberWithFormat claim = (NumberWithFormat)
                JwtUtils.parseToken(token).getClaim(JWTPayload.EXPIRES_AT);
        long expireTime= Convert.toDate(claim).getTime();
        AuthenticationResult authenticationResult
                =new AuthenticationResult(user.getId(),
                ResultCode.SUCCESS,token,expireTime);
        // 将认证结果转换为JSON字符串
        String result= JSON.toJSONString(authenticationResult,
                SerializerFeature.DisableCircularReferenceDetect);
        // 向客户端发送认证结果
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        // 将token存入Redis，设置过期时间
        String tokenKey="token:"+token;
        long nowTime= DateTime.now().getTime();
        redisUtils.set(tokenKey,token,(expireTime-nowTime)/1000);
    }
}
