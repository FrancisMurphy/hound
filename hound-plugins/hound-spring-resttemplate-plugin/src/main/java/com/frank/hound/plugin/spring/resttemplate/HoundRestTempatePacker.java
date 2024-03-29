package com.frank.hound.plugin.spring.resttemplate;

import com.frank.hound.core.entity.HoundTraceContext;
import com.frank.hound.core.keeper.TraceContextThreadLocalKeeper;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * @author frank
 */
public class HoundRestTempatePacker implements ClientHttpRequestInterceptor
{
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException
    {
        //获取现场上下文中的traceId
        HoundTraceContext traceContext = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT
                .get();

        if (null == traceContext)
        {
            return execution.execute(request, body);
        }

        for (Map.Entry<String, String> contextEntry : traceContext
                .getContexts())
        {
            final String contextKey = contextEntry.getKey();
            final String contextValue = contextEntry.getValue();
            request.getHeaders().add(contextKey, contextValue);
        }
        return execution.execute(request, body);
    }
}
