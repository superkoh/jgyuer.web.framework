package com.jgyuer.framework.api.handler;

import com.jgyuer.framework.api.response.BizRes;
import com.jgyuer.framework.api.response.ErrorRes;
import com.jgyuer.framework.api.response.SuccessRes;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

abstract public class AbstractResponseBodyHandler implements ResponseBodyAdvice<BizRes> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public BizRes beforeBodyWrite(BizRes body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        String deviceToken = response.getHeaders().getFirst("X-Jgyuer-Device-Token");
        if (body instanceof ErrorRes) {
            ((ErrorRes) body).setVd(deviceToken);
            return body;
        }
        if (body instanceof SuccessRes) {
            ((SuccessRes) body).setVd(deviceToken);
            return body;
        }
        SuccessRes res = new SuccessRes(body);
        res.setVd(deviceToken);
        return res;
    }
}
