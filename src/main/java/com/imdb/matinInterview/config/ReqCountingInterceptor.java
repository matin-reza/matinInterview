package com.imdb.matinInterview.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ReqCountingInterceptor implements HandlerInterceptor {

    private final AtomicInteger httpRequestCount = new AtomicInteger(0);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        httpRequestCount.incrementAndGet();
        return true;
    }

    public Integer getRequestCount() {
        return httpRequestCount.get();
    }
}
