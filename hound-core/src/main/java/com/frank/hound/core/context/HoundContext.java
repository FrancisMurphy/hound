package com.frank.hound.core.context;

import com.frank.hound.core.acceptor.sorter.Sort;
import com.frank.hound.core.acceptor.sorter.SorterInitializer;
import com.frank.hound.core.container.HoundBasicContainer;
import com.frank.hound.core.container.HoundComponentContainer;

/**
 * 初始化并持有容器等重要实例的上下文
 * @author frank
 */
public class HoundContext implements HoundComponentContext, Sort
{
    private static HoundContext context = new HoundContext();

    private HoundComponentContainer componentContainer;

    private Sort firstSorter;


    private HoundContext()
    {
        componentContainer = new HoundComponentContainer();
        SorterInitializer sorterInitializer = new SorterInitializer();
        firstSorter = sorterInitializer.getFirstSorter();
    }

    @Override
    public <T> T getComponent(String componentName,Class<T> componentClazz)
    {
        HoundBasicContainer<T> basicContainer = componentContainer.getCompontsContainer(componentClazz);
        if(basicContainer!=null)
        {
            return basicContainer.get(componentName);
        }
        return null;
    }

    public static HoundContext getContext()
    {
        return context;
    }

    @Override
    public void sort()
    {
        firstSorter.sort();
    }
}
