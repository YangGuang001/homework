package TestCountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by yz on 2017/5/30.
 */
public class ApplicationStartUpDemo {
    private CountDownLatch latch;

    private List<BaseFramework> arrayList;

    public static ApplicationStartUpDemo Instance = new ApplicationStartUpDemo();

    private ApplicationStartUpDemo(){
    }

    public static ApplicationStartUpDemo getInstance(){
        return Instance;
    }

    public boolean serviceStartUp(){
        latch = new CountDownLatch(1);

        arrayList = new ArrayList<BaseFramework>((int) latch.getCount());

        arrayList.add(new DataSourceService(latch));

        Executor executors = Executors.newFixedThreadPool(arrayList.size());

        for (BaseFramework service:arrayList){
            executors.execute(service);
        }

        try {
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        for (BaseFramework service:arrayList){
            if (!service.IsServiceUp()){
                return false;
            }
        }
        return true;
    }
}
