package com.frank.hound.core.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HoundTraceContext
{
    /**
     * The map that keep context param
     * 1."trace_id" {@see com.frank.trace.link.constant.TraceContextConstant}
     */
    private Map<String,String> contextMap;

    public String getContext(String contextKey)
    {
        if(contextMap!=null)
        {
            return contextMap.get(contextKey);
        }
        return null;
    }

    public void addContext(String contextKey, String contextValue)
    {
        if(null == contextMap)
        {
            contextMap = new HashMap<>(5);
        }
        this.contextMap.put(contextKey,contextValue);
    }

    public Set<Map.Entry<String,String>>getContexts()
    {
        if(null == contextMap)
        {
            return new HashSet<>();
        }
        return contextMap.entrySet();
    }
}
