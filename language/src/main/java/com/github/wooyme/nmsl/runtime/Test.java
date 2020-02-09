package com.github.wooyme.nmsl.runtime;

public class Test implements Callable {
    @Override
    public Object execute(Object instance,Object[] args) { return (((java.lang.Throwable)instance).getMessage()); }
}
