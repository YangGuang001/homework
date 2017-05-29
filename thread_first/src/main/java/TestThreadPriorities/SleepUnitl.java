package TestThreadPriorities;

import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2017/5/29.
 */
public class SleepUnitl {
    public static final void SleepSecond(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
