package com.jgyuer.framework.api.interceptor;

import com.jgyuer.framework.exception.PermissionDeniedException;
import com.jgyuer.framework.runtime.RuntimeEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private static final String AUTH_STRING = "d157f184b7ae9ba56c8cfdbdcaf531bb";
    @Autowired
    private RuntimeEnv runtimeEnv;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String auth = request.getHeader("X-Jgyuer-Auth");
        if (null == auth || !AUTH_STRING.equals(auth)) {
            throw new PermissionDeniedException();
        }
        return true;
    }
}
