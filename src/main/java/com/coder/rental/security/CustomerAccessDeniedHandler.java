package com.coder.rental.security;//package com.coder.rental.security;

import com.alibaba.fastjson.JSON;
import com.coder.rental.utils.Result;
import com.coder.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            ServletOutputStream outputStream = response.getOutputStream();
            String result =
                    JSON.toJSONString(Result.fail().setCode(ResultCode.UNAUTHORIZED)
                            .setMessage("无权访问"));
            outputStream.write(result.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
    }
}
