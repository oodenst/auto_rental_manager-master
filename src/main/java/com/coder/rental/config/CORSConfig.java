package com.coder.rental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 实现WebMvcConfigurer接口，用于配置CORS跨域
 */
@Configuration
public class CORSConfig implements WebMvcConfigurer {
    /**
     * 为所有的请求路径添加CORS跨域配置
     *
     * @param registry CorsRegistry对象，用于注册跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//配置跨域请求的映射
        registry.addMapping("/**")
//允许所有来源的跨域请求
                .allowedOriginPatterns("*")
//允许所有请求头
                .allowedHeaders("*")
//允许所有请求方法
                .allowedMethods("*")
//允许请求携带认证信息 (如cookie)
                .allowCredentials(true)
//跨域请求的缓存时间
                .maxAge(3600);
    }
}
