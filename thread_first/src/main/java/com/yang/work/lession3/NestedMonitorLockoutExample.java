package com.yang.work.lession3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * Created by yz on 2017/9/12.
 */
public class NestedMonitorLockoutExample {
    public static void main(String[] args) {
        final Helper helper = new Helper();

        Thread t = new Thread(new Runnable() {
            public void run() {
                String result = helper.xGuarededMethod("test");
            }
        });

        t.start();

        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                helper.xSateChanged();
                timer.cancel();
            }
        }, 50, 10);
    }

    private static class Helper {
        private volatile boolean isStateOk = false;

        private final Predicate stateBeOK = new Predicate() {
            public boolean evalute() {
                return isStateOk;
            }
        };

        private final Blocker blocker = new ConditionVarBlocker();

        public String xGuarededMethod(final String message) {
            GuardedAction<String> ga = new GuardedAction<String>(stateBeOK) {
                public String call() throws Exception {
                    return message + "->received";
                }
            };

            String result = null;

            try {
                result = blocker.callWithGuard(ga);
            }catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        public void xSateChanged() {
            try {
                blocker.signalAfter(new Callable<Boolean>() {
                    public Boolean call() throws Exception {
                        isStateOk = true;
                        return Boolean.TRUE;
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
