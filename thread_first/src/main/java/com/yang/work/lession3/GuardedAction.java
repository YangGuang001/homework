package com.yang.work.lession3;

import java.util.concurrent.Callable;

/**
 * Created by yz on 2017/9/12.
 */
public abstract class GuardedAction<V> implements Callable<V> {
    protected final Predicate guard;

    public GuardedAction(Predicate guard) {
        this.guard = guard;
    }
}
