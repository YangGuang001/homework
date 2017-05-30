package TestCountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yz on 2017/5/30.
 */

public abstract class BaseFramework implements Runnable{
    private CountDownLatch latch;
    private boolean isServiceUp;
    private String serviceName;

    public BaseFramework(CountDownLatch latch, String serviceName) {
        this.latch = latch;
        this.serviceName = serviceName;
    }

    public String getServiceName(){
        return this.serviceName;
    }

    public boolean IsServiceUp(){
        return this.isServiceUp;
    }
    public void run() {
        try {
            isServiceUp = true;
            serviceWork();
        }catch (Throwable e){
            isServiceUp = false;
            e.printStackTrace();
        }finally {
            if (latch != null){
                latch.countDown();
            }
        }
    }

    public abstract void serviceWork();
}
