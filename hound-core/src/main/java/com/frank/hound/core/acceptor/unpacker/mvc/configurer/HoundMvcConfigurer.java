package com.frank.hound.core.acceptor.unpacker.mvc.configurer;

import com.frank.hound.core.acceptor.unpacker.mvc.interceptor.MvcTraceInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author frank
 */
public class HoundMvcConfigurer implements WebMvcConfigurer
{
    private MvcTraceInterceptor mvcTraceInterceptor;

    public HoundMvcConfigurer()
    {
    }

    public HoundMvcConfigurer(
            MvcTraceInterceptor mvcTraceInterceptor)
    {
        this.mvcTraceInterceptor = mvcTraceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(mvcTraceInterceptor);
        addInterceptor.addPathPatterns("/**");
    }
}