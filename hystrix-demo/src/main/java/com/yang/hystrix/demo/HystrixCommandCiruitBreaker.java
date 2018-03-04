package com.yang.hystrix.demo;

import com.netflix.hystrix.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yz on 2018/2/25.
 */
public class HystrixCommandCiruitBreaker extends HystrixCommand<String> {
    private final String name;

    public HystrixCommandCiruitBreaker(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CiruitBreaker"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("CiruitBreakerTestKey"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakerTest"))
                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                                        .withCoreSize(200)
                    )
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                            .withCircuitBreakerEnabled(true)
                                            .withCircuitBreakerRequestVolumeThreshold(3)
                                            .withCircuitBreakerErrorThresholdPercentage(80)
                    //                		.withCircuitBreakerForceOpen(true)	// 置为true时，所有请求都将被拒绝，直接到fallback
                    //                		.withCircuitBreakerForceClosed(true)	// 置为true时，将忽略错误
                    //                		.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
                    //                		.withExecutionTimeoutInMilliseconds(5000)
                    )
        );
        this.name = name;
    }

    protected String run() throws Exception {
        System.out.println("running run():" + name);
        int num = Integer.valueOf(name);
        if(num % 2 == 0 && num < 10) {	// 直接返回
            return name;
        } else {	// 无限循环模拟超时
            int j = 0;
            while (true) {
                j++;
            }
        }
//		return name;
    }

    @Override
    protected String getFallback() {
        return "CircuitBreaker fallback: " + name;
    }

    public static class UnitTest {
        @Test
        public void testSynchronous() throws IOException {
            for(int i = 0; i < 50; i++) {
                try {
                    System.out.println("===========" + new HystrixCommandCiruitBreaker(String.valueOf(i)).execute());
//	        		try {
//	            		TimeUnit.MILLISECONDS.sleep(1000);
//	            	}catch(Exception e) {}
//	        		Future<String> future = new HystrixCommand4CircuitBreakerTest("Hlx"+i).queue();
//	        		System.out.println("===========" + future);
                } catch(Exception e) {
                    System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
                }
            }

            System.out.println("------开始打印现有线程---------");
            Map<Thread, StackTraceElement[]> map=Thread.getAllStackTraces();
            for (Thread thread : map.keySet()) {
                System.out.println(thread.getName());
            }
            System.out.println("thread num: " + map.size());

            System.in.read();
        }
    }
}
