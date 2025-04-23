package com.coder.rental.security;//package com.coder.rental.security;

import com.alibaba.fastjson.JSON;
import com.coder.rental.utils.Result;
import com.coder.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    /**
     * 处理登录失败的情况。
     *
     * @param request HttpServletRequest对象，代表客户端的请求
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @param exception 登录失败抛出的异常对象
     * @throws IOException 如果发生I/O错误
     * @throws ServletException 如果发生Servlet相关错误
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // 设置响应内容类型为JSON，编码方式为UTF-8
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        // 初始化默认的错误代码和消息
        int code= ResultCode.ERROR;
        String msg=null;
        // 根据不同的异常类型设置不同的错误代码和消息
        if (exception instanceof AccountExpiredException){
            code=ResultCode.UNAUTHENTICATED;
            msg="账户过期";
        }else if (exception instanceof BadCredentialsException){
            code= ResultCode.UNAUTHORIZED;
            msg="账户或密码错误";
        }else if (exception instanceof CredentialsExpiredException){
            code=ResultCode.UNAUTHORIZED;
            msg="密码过期";
        }else if (exception instanceof DisabledException){
            code=ResultCode.UNAUTHORIZED;
            msg="账户被禁用";
        }else if (exception instanceof LockedException){
            code=ResultCode.UNAUTHORIZED;
            msg="账户被锁定";
        }else if (exception instanceof InternalAuthenticationServiceException){
            code=ResultCode.UNAUTHORIZED;
            msg="账户不存在";
        }else if (exception instanceof CustomerAuthenticationException){
            code=ResultCode.UNAUTHORIZED;
            msg=exception.getMessage();
        }
        else {
            msg="登录失败";
        }
// 使用JSON格式化错误信息并写入响应体
        String result= JSON.toJSONString(Result.fail().setCode(code).setMessage(msg));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
