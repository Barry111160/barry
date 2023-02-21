package com.musclebeach.common.config;

import com.musclebeach.common.controller.interceptor.CartInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

public class SpringMvcSupport extends WebMvcConfigurationSupport {

    @Resource
    private CartInterceptor cartInterceptor;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 當訪問靜態資源時，不經由mvc。
        registry.addResourceHandler("/front-end/**").addResourceLocations("/front-end/");
        registry.addResourceHandler("/back-end/**").addResourceLocations("/back-end/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cartInterceptor).addPathPatterns("/carts");
    }
}
