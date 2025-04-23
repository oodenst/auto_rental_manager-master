package com.coder.rental.config;

import com.coder.rental.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 定义一个全局的异常处理类
@Slf4j // 使用lombok提供的@Slf4j注解，方便记录日志
public class GlobalException {
    /**
     * 处理所有继承自Exception的异常。
     * @param e 抛出的异常对象
     * @return 返回一个表示操作失败的结果对象，其中包含异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        log.error("异常信息：{}", e.getMessage()); // 记录异常信息到日志
        e.printStackTrace();
        return Result.fail().setMessage(e.getMessage()); // 返回一个失败的结果，并设置失败信息为异常信息
    }
}
