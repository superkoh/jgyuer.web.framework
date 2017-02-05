package com.jgyuer.framework.api.interceptor;

import com.jgyuer.framework.api.annotation.GuestRequired;
import com.jgyuer.framework.api.annotation.LoginRequired;
import com.jgyuer.framework.exception.NeedGuestException;
import com.jgyuer.framework.exception.NotLoginException;
import com.jgyuer.framework.runtime.env.RuntimeEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RuntimeEnv runtimeEnv;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (runtimeEnv.getLoginUser().getId() < 1 || runtimeEnv.getLoginUser().getTokenExpireTime().isBefore
                (LocalDateTime.now())) {
            if (handlerMethod.getMethod().isAnnotationPresent(LoginRequired.class)) {
                throw new NotLoginException();
            }
        } else {
            if (handlerMethod.getMethod().isAnnotationPresent(GuestRequired.class)) {
                throw new NeedGuestException();
            }
        }
        return true;
    }
}
