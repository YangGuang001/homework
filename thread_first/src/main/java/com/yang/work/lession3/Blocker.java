package com.yang.work.lession3;

import java.util.concurrent.Callable;

/**
 * Created by yz on 2017/9/12.
 */
public interface Blocker {
    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    void signalAfter(Callable<Boolean> stateOpration) throws Exception;

    void signal() throws InterruptedException;

    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
