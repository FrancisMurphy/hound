package com.frank.hound.plugin.spring.mvc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.frank.hound.core.acceptor.unpacker.BasicUnpacker;
import com.frank.hound.core.annotation.HoundComponent;
import com.frank.hound.core.assistant.TraceContextAssistant;
import com.frank.hound.core.entity.HoundTraceContext;
import com.frank.hound.core.keeper.TraceContextThreadLocalKeeper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author frank
 */
@HoundComponent("mvc")
public class MvcUnpacker extends BasicUnpacker
{

    @Override
    protected void unpacking(Object... unpackParams)
    {

        if(!(1==unpackParams.length &&
                unpackParams[0] instanceof HttpServletRequest))
        {
            return;
        }

        HttpServletRequest request = (HttpServletRequest) unpackParams[0];

        try
        {
            TransmittableThreadLocal<HoundTraceContext> traceContextThreadLocal = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT;

            //获取上下文参数
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null)
            {
                while (headerNames.hasMoreElements())
                {
                    final String headerName = headerNames.nextElement();
                    if (TraceContextAssistant.isTraceKeyContain(headerName))
                    {
                        final String headerValue = request
                                .getHeader(headerName);
                        //放入线程上下文
                        HoundTraceContext traceContext = new HoundTraceContext();
                        traceContext.addContext(headerName, headerValue);
                        traceContextThreadLocal.set(traceContext);
                    }
                }
            }
        }
        catch (Exception e)
        {
            //do nothing
        }
    }
}
