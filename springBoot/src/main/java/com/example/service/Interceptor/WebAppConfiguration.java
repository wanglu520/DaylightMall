package com.example.service.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String path = System.getProperty("user.dir")+"\\springBoot\\src\\main\\resources\\static\\picture\\mall";
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            registry.addResourceHandler("/picture/**")
                    .addResourceLocations("file:"+path);
        }else {
            registry.addResourceHandler("/picture/**").
                    addResourceLocations("file:"+path);

        }
    }
}
