package com.frank.hound.plugin.acceptor.sorter.logback;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.frank.hound.core.acceptor.sorter.ChainSorter;
import com.frank.hound.core.annotation.HoundComponent;
import com.frank.hound.core.constant.TraceContextConstant;
import com.frank.hound.core.entity.HoundTraceContext;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author frank
 */
@HoundComponent("logback")
public class LogbackSorter extends ChainSorter
{
    public LogbackSorter()
    {
        super();
    }

    @Override
    protected void sorting(TransmittableThreadLocal<HoundTraceContext> traceContextThreadLocal)
    {
        if(null == traceContextThreadLocal.get() || StringUtils
                .isEmpty(traceContextThreadLocal.get().getContext(
                        TraceContextConstant.TRACE_CONTEXT_HEAD)))
        {
            String newTraceId = initTraceId(traceContextThreadLocal);

            MDC.put(TraceContextConstant.TRACE_CONTEXT_HEAD, newTraceId);
        }
        else
        {
            MDC.put(TraceContextConstant.TRACE_CONTEXT_HEAD, traceContextThreadLocal.get().getContext(
                    TraceContextConstant.TRACE_CONTEXT_HEAD));
        }
    }

    /**
     * Init traceId that can not find traceId in trace context...
     * @param traceContextThreadLocal
     * @return
     */
    private String initTraceId(
            TransmittableThreadLocal<HoundTraceContext> traceContextThreadLocal)
    {
        UUID uuid = UUID.randomUUID();
        String newTraceId = uuid.toString().replace("-", "");

        HoundTraceContext traceContext = new HoundTraceContext();
        traceContext.addContext(TraceContextConstant.TRACE_CONTEXT_HEAD, newTraceId);
        traceContextThreadLocal.set(traceContext);
        return newTraceId;
    }
}
