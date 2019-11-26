package com.oracle.truffle.sl.runtime;

public interface Callable {
    public Object execute(Object instance,Object[] args);
}
