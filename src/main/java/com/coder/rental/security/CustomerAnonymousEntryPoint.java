package com.coder.rental.security;//package com.coder.rental.security;

import com.alibaba.fastjson.JSON;
import com.coder.rental.utils.Result;
import com.coder.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
/**
 * 匿名用户访问无权限资源处理器
 */
@Component
public class CustomerAnonymousEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws
            IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String result= JSON.toJSONString(Result.fail().setCode(ResultCode.UNAUTHORIZED)
                        .setMessage("匿名用户无权访问"));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}