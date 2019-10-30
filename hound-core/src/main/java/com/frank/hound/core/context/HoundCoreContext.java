package com.frank.hound.core.context;

import com.frank.hound.core.acceptor.sorter.Sort;
import com.frank.hound.core.acceptor.sorter.SorterLoader;
import com.frank.hound.core.container.HoundBasicContainer;
import com.frank.hound.core.container.HoundComponentContainer;

/**
 * 初始化并持有容器等重要实例的上下文，作用类似于ApplicationContext
 * @author frank
 */
public class HoundCoreContext implements HoundComponentContext, Sort
{
    private static HoundCoreContext context = new HoundCoreContext();

    private HoundComponentContainer componentContainer;

    private Sort firstSorter;

    private HoundCoreContext()
    {
        componentContainer = new HoundComponentContainer();
        SorterLoader sorterLoader= new SorterLoader();
        firstSorter = sorterLoader.getFirstSorter();
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

    public static HoundCoreContext getContext()
    {
        return context;
    }

    @Override
    public void sort()
    {
        firstSorter.sort();
    }
}
