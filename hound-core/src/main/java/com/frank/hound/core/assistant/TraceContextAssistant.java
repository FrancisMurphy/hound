package com.frank.hound.core.assistant;

import com.frank.hound.core.constant.TraceContextConstants;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class TraceContextAssistant
{
    private static Set<String> traceContextKeys = new HashSet<>();

    static
    {
        Field[] traceContextFields = TraceContextConstants.class.getFields();

        try
        {
            for(Field traceContextField : traceContextFields)
            {
                String traceContextKey = (String)traceContextField.get(null);
                if(!StringUtils.isEmpty(traceContextKey))
                {
                    traceContextKeys.add(traceContextKey);
                }
            }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
    }
    }

    public static boolean isTraceKeyContain(String key)
    {
        return traceContextKeys.contains(key);
    }

}
