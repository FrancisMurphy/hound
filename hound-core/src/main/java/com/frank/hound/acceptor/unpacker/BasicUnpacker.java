package com.frank.hound.acceptor.unpacker;

import com.frank.hound.core.context.HoundContext;

/**
 * @author frank
 */
public abstract class BasicUnpacker implements Unpack
{
    public BasicUnpacker()
    {
    }

    protected abstract void unpacking(Object... unpackParams);

    @Override
    public void unpack(Object... unpackParams)
    {
        unpacking(unpackParams);
        HoundContext.getContext().sort();
    }

}
