package com.jgyuer.framework.api.interceptor;

import com.jgyuer.framework.api.annotation.PrivateNetworkOnly;
import com.jgyuer.framework.exception.PermissionDeniedException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivateNetworkInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.getMethod().isAnnotationPresent(PrivateNetworkOnly.class)) {
            if (!request.getLocalAddr().startsWith("127.") && !request.getLocalAddr().startsWith("10.")) {
                throw new PermissionDeniedException();
            }
            if (!request.getRemoteAddr().startsWith("127.") && !request.getRemoteAddr().startsWith("10.")) {
                throw new PermissionDeniedException();
            }
        }
        return true;
    }
}
