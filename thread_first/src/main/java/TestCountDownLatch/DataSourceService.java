package TestCountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2017/5/30.
 */
public class DataSourceService extends BaseFramework {
    public DataSourceService(CountDownLatch latch) {
        super(latch, "数据源");
    }

    public void serviceWork() {
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("数据源启动成功!!!");
    }
}
