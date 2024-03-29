package com.frank.hound.spi;

import com.frank.hound.plugin.springmvc.configurer.HoundMvcConfigurer;
import com.frank.hound.plugin.springmvc.interceptor.MvcTraceInterceptor;
import com.frank.hound.plugin.requester.packer.feign.HoundFeignClientPacker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author frank
 */
@Configuration
public class HoundAutoConfiguration
{
    /**
     * MVC
     */
    @Bean
    public MvcTraceInterceptor mvcTraceInterceptor()
    {
        return new MvcTraceInterceptor();
    }

    @Bean
    @ConditionalOnBean(value = {MvcTraceInterceptor.class})
    public HoundMvcConfigurer mvcWebMvcConfigurer(MvcTraceInterceptor mvcTraceInterceptor)
    {
        return new HoundMvcConfigurer(mvcTraceInterceptor);
    }

    /**
     * feign
     */
    @Bean
    public HoundFeignClientPacker feignTraceInterceptor()
    {
        return new HoundFeignClientPacker();
    }

}
