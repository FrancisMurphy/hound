package com.frank.hound.core.context;

public interface HoundComponentContext
{
    <T> T getComponent(String componentName, Class<T> componentClazz);
}
