package com.jgyuer.framework.api.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by KOH on 2017/4/4.
 * <p>
 * webFramework
 */
public class CorsInterceptor extends HandlerInterceptorAdapter {
    private List<String> allowedOrigins;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String origin = request.getHeader("Origin");
        if (null == origin || origin.isEmpty()) {
            return true;
        }
        if (null == allowedOrigins || allowedOrigins.isEmpty()) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        } else {
            if (allowedOrigins.contains(origin.trim())) {
                response.setHeader("Access-Control-Allow-Origin", origin);
            } else {
                response.setHeader("Access-Control-Allow-Origin", allowedOrigins.get(0));
            }
        }
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Access-Control-Allow-Headers", "content-type,x-jgyuer-auth, x-jgyuer-device-token,x-jgyuer-user-token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
