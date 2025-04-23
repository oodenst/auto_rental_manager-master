package com.coder.rental.security;//package com.coder.rental.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Data
public class PasswordConfig {
    // 通过@Value注解从应用的配置文件中动态注入加密强度值。
    @Value("${encoder.ctype.strength}")
    private int strength;
    // 通过@Value注解从应用的配置文件中动态注入加密使用的密钥。
    @Value("${encoder.ctype.secret}")
    private String secret;
    /**
     * 创建并返回一个BCryptPasswordEncoder实例。
     * 此密码编码器使用指定的加密强度和基于secret参数生成的SecureRandom实例。
     *
     * @return BCryptPasswordEncoder 返回一个配置好的BCryptPasswordEncoder实例。
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        // 使用secret生成一个SecureRandom实例，以增加加密过程的随机性。
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        return new BCryptPasswordEncoder(strength,secureRandom);
//        return new BCryptPasswordEncoder();
    }
//    spring security找到UserDetailsService，调用里面loadUserByUsername方法，如果方法抛异常，重
//    定向到/login?error，表示用户名不存在，登录失败。如果方法没有抛异常，调用matches方法
//    （CustomerPasswordEncoder类实现了PasswordEncode接口中matches方法），
//    校验返回值中保存的密码和请求参数的密码是否匹配，如果为true，则登录成功，否则登录失败
//    public static void main(String[] args) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new PasswordConfig().passwordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("123456"));
//        System.out.println(bCryptPasswordEncoder.encode("123456"));
//    }

}
