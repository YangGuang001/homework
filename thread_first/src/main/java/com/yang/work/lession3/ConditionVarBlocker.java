package com.yang.work.lession3;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yz on 2017/9/12.
 */
public class ConditionVarBlocker implements Blocker {
    private final Lock lock;

    private final Condition condition;

    public ConditionVarBlocker(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public ConditionVarBlocker() {
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception {
        lock.lockInterruptibly();
        try {
            final Predicate guard = guardedAction.guard;
            while (!guard.evalute()) {
                condition.await();
            }
            return guardedAction.call();
        }finally {
            lock.unlock();
        }
    }

    public void signalAfter(Callable<Boolean> stateOpration) throws Exception {
        lock.lockInterruptibly();
        try {
            if (stateOpration.call()) {
                condition.signal();
            }
        }finally {
            lock.unlock();
        }
    }

    public void signal() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public void broadcastAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();
        try {
            if (stateOperation.call()) {
                condition.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }
}
