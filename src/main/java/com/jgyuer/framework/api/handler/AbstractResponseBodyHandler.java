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
        if (body instanceof ErrorRes) {
            return body;
        }
        if (body instanceof SuccessRes) {
            return body;
        }
        return new SuccessRes(body);
    }
}
