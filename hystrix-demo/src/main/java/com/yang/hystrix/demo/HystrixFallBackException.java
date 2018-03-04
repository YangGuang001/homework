package com.yang.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yz on 2018/2/25.
 */
public class HystrixFallBackException extends HystrixCommand<String> {

    private final String name;

    public HystrixFallBackException(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("FallBackGroup"));
        this.name = name;
    }

    protected String run() throws Exception {
        /*---------------会触发fallback的case-------------------*/
        // 无限循环，实际上属于超时
//    	int j = 0;
//    	while (true) {
//    		j++;
//    	}

        // 除零异常
//    	int i = 1/0;

        // 主动抛出异常
        throw new HystrixTimeoutException();
//        throw new RuntimeException("this command will trigger fallback");
//        throw new Exception("this command will trigger fallback");
//    	throw new HystrixRuntimeException(FailureType.BAD_REQUEST_EXCEPTION, commandClass, message, cause, fallbackException);

    	/*---------------不会触发fallback的case-------------------*/
        // 被捕获的异常不会触发fallback
//    	try {
//    		throw new RuntimeException("this command never trigger fallback");
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    	}

        // HystrixBadRequestException异常由非法参数或非系统错误引起，不会触发fallback，也不会被计入熔断器
        //throw new HystrixBadRequestException("HystrixBadRequestException is never trigger fallback");
        //throw new Exception("call error test");
//		return name;
        //return "error";
    }

    @Override
    protected String getFallback() {
        return "fallback: " + name;
    }

    public static class UnitTest {
        @Test
        public void testSynchronous() {
            try {
                String result = new HystrixFallBackException("Hlx").execute();
                System.out.println(result);
                assertEquals("fallback: Hlx", result);
            } catch(Exception e) {
                System.out.println("run()抛出HystrixBadRequestException时，会被捕获到这里" + e.getCause());
            }
//        	System.in.read();
        }
    }
}
