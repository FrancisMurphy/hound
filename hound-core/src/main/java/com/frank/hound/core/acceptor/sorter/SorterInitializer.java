package com.frank.hound.core.acceptor.sorter;

import com.frank.hound.core.util.ReflectUtils;
import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Sorter manager
 * @author frank
 */
public class SorterInitializer
{
    @Getter
    private ChainSorter firstSorter;

    public SorterInitializer()
    {
        initSorters();
    }

    private void initSorters()
    {
        LinkedList<ChainSorter> sorters = null;
        try
        {
            sorters = ReflectUtils.getAllChildInstanceByClass(ChainSorter.class);
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            return;
            //TODO:专用异常体系
        }

        if(null == sorters)
        {
            return;
        }

        Iterator<ChainSorter> iterator=sorters.iterator();
        ChainSorter oldSorter = null;
        while(iterator.hasNext()){
            if(oldSorter != null)
            {
                ChainSorter newGuy = iterator.next();
                oldSorter.setNextSorter(newGuy);
                oldSorter = newGuy;
            }
            else
            {
                oldSorter = iterator.next();
            }
        }

        firstSorter = sorters.getFirst();
    }
}
