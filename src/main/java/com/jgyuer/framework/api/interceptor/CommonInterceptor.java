package com.jgyuer.framework.api.interceptor;

import com.jgyuer.autoconfig.rtenv.WebRuntimeEnv;
import com.jgyuer.framework.runtime.RuntimeEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
            WebRuntimeEnv env = (WebRuntimeEnv) runtimeEnv;
            env.setRemoteIp(getRemoteIpAddress(request));
            env.setUserAgent(request.getHeader("User-Agent"));
            String deviceToken = request.getHeader("X-Jgyuer-Device-Token");
            if (null == deviceToken || deviceToken.isEmpty()) {
                Cookie[] cookies = request.getCookies();
                if (null != cookies && cookies.length > 0) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("jgsid")) {
                            deviceToken = cookie.getValue();
                            break;
                        }
                    }
                }
            }
            if (null == deviceToken || deviceToken.isEmpty()) {
                deviceToken = UUID.randomUUID().toString();
                response.setHeader("X-Jgyuer-Device-Token", deviceToken);
                Cookie cookie = new Cookie("jgsid", deviceToken);
                response.addCookie(cookie);
            }
            env.setState("deviceToken", deviceToken);
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
