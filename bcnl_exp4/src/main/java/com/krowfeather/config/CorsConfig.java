package com.krowfeather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有接口
                .allowedOrigins("http://localhost:5173") // 允许前端域名
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许请求方法

                .allowCredentials(true); // 允许携带Cookie/Token
    }
}