package com.yang.springretry.demo;

import org.junit.Test;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class TestSpringRetry {
    @Retryable
    @Test
    public void testSimpleRetry() throws Throwable {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(3);

        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        Integer ressult = retryTemplate.execute(new RetryCallback<Integer, Throwable>() {
            private int i = 0;

            public Integer doWithRetry(RetryContext retryContext) throws Throwable {
                System.out.println("retry count:" + retryContext.getRetryCount());
                return len(i++);
            }
        }, new RecoveryCallback<Integer>() {
            public Integer recover(RetryContext retryContext) throws Exception {
                System.out.println("recovery method call, count " + retryContext.getRetryCount());
                return Integer.MAX_VALUE;
            }
        });
    }

    private int len(int i) throws Exception {
        if (i < 10) throw new Exception(i + " le 10");
        return i;
    }
}
