package com.jgyuer.framework.api.interceptor;

import com.jgyuer.framework.security.service.LoginUserService;
import com.jgyuer.framework.security.subject.LoginUser;
import com.jgyuer.framework.runtime.env.RuntimeEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor<T extends LoginUserService> extends HandlerInterceptorAdapter {
    @Autowired
    private RuntimeEnv runtimeEnv;

    private LoginUserService loginUserService;

    public UserInterceptor(T loginUserService) {
        this.loginUserService = loginUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("X-Jgyuer-User-Token");
        if (null != token) {
            LoginUser loginUser = loginUserService.getUserByToken(token);
            if (null != loginUser) {
                runtimeEnv.setLoginUser(loginUser);
            }
        }
        return true;
    }
}
