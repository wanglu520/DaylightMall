package com.example.service.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new AuthorizationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login");
    }
}
