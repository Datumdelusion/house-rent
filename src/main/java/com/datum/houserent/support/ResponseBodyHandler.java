package com.datum.houserent.support;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author violetfreesia
 * @date 2020-12-07
 */
@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        boolean isPackage = false;
        final Method method = returnType.getMethod();
        Assert.notNull(method, "未获取到方法");
        Class<?> methodClass = method.getDeclaringClass();
        if (methodClass.isAnnotationPresent(ResponsePack.class)) {
            isPackage = true;
        }
        return isPackage;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return ResponsePackage.success(body);
    }
}
