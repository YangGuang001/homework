package test.synchronizedTest;

/**
 * Created by yz on 2017/5/19.
 */
public class MtThread1 extends Thread{
    private Task task;

    public MtThread1(Task task) {
        this.task = task;
    }

    public void run(){
        super.run();
        CommonUtils.beginTime1 = System.currentTimeMillis();
        task.doSomething();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}
