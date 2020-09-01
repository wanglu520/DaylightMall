package com.example.service.Interceptor;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.Utils.JwtUtils;
import com.example.service.Utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;
    Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    //Header名称
    private String Authorization = "Authorization";
    //失败返回码
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;
    private static AuthorizationInterceptor authorizationInterceptor;
    @PostConstruct
    public void init(){
        authorizationInterceptor = this;
        authorizationInterceptor.jwtUtils = this.jwtUtils;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String token = request.getHeader(Authorization);
        if(StringUtils.isNotEmpty(token)){
            if(authorizationInterceptor.jwtUtils.verify(token)){
                response.setStatus(unauthorizedErrorCode);
                throw new RuntimeException("失效，请重新登录!");
            }
        }else {
            response.setStatus(unauthorizedErrorCode);
            throw new RuntimeException("未登录，请重新登录!");
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
