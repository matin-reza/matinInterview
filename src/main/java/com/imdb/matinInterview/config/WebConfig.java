package com.imdb.matinInterview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ReqCountingInterceptor reqCountingInterceptor;

    public WebConfig(ReqCountingInterceptor reqCountingInterceptor) {
        this.reqCountingInterceptor = reqCountingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reqCountingInterceptor);  // Add the interceptor
    }
}
