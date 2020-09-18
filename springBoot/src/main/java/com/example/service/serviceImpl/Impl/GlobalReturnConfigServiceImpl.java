package com.example.service.serviceImpl.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.service.Interceptor.AuthorizationInterceptor;
import com.example.service.OutPutObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//@EnableWebMvc
@Configuration
public class GlobalReturnConfigServiceImpl {
    private static final Map<String, Object> bean = new HashMap<>();
    private static final List<Map<String, Object>> beans = new ArrayList<>();

    @RestControllerAdvice
    static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        //统一处理返回前端的数据;
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
            String contentType = selectedContentType.toString();
            if("application/json".equals(contentType)){
                OutPutObject out = (OutPutObject)(body);
                out.setReturnCode(out.getReturnCode() != null ? out.getReturnCode() : "0");
                out.setBean(out.getBean() != null ? out.getBean() : bean);
                out.setBeans(out.getBeans() != null ? out.getBeans() : beans);
                return out;
            }
            return body;
        }


    }
}
