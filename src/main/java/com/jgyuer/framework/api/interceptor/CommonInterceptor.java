package com.jgyuer.framework.api.interceptor;

import com.jgyuer.framework.runtime.RuntimeEnv;
import com.jgyuer.autoconfig.rtenv.WebRuntimeEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RuntimeEnv runtimeEnv;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (runtimeEnv instanceof WebRuntimeEnv) {
            ((WebRuntimeEnv) runtimeEnv).setRemoteIp(getRemoteIpAddress(request));
            ((WebRuntimeEnv) runtimeEnv).setUserAgent(request.getHeader("User-Agent"));
        }
        return true;
    }

    private static String getRemoteIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("g-remote-ip");
        if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (null == ip || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
