package com.frank.hound.plugin.spring.mvc.interceptor;

import com.frank.hound.core.acceptor.unpacker.Unpack;
import com.frank.hound.core.context.HoundContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author frank
 */
public class MvcTraceInterceptor implements HandlerInterceptor
{

    private Unpack mvcUnpacker;

    public MvcTraceInterceptor()
    {
        mvcUnpacker = HoundContext
                .getContext().getComponent("mvc",Unpack.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler)
    {
        mvcUnpacker.unpack(request);
        return true;
    }
}
