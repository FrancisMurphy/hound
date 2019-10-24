package com.frank.hound.core.acceptor.sorter;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.frank.hound.core.entity.HoundTraceContext;
import com.frank.hound.core.keeper.TraceContextThreadLocalKeeper;
import lombok.Setter;

/**
 * Chain of responsibility
 * @author frank
 */
public abstract class ChainSorter implements Sort
{
    @Setter
    private ChainSorter nextSorter;

    TransmittableThreadLocal<HoundTraceContext> traceContextThreadLocal = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT;

    public ChainSorter()
    {
    }

    @Override
    public void sort()
    {
        try
        {
            sorting(traceContextThreadLocal);
            if(nextSorter!=null)
            {
                nextSorter.sort();
            }
        }
        catch (Exception e)
        {
            //TODO：缺少专用异常体系
        }

    }

    protected abstract void sorting(TransmittableThreadLocal<HoundTraceContext> traceContextThreadLocal);

}
