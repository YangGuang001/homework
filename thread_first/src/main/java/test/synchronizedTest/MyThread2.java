package test.synchronizedTest;

/**
 * Created by yz on 2017/5/19.
 */
public class MyThread2 extends Thread {
    private Task task;

    public MyThread2(Task task) {
        this.task = task;
    }

    public void run(){
        CommonUtils.beginTime2 = System.currentTimeMillis();
        task.other();
        CommonUtils.endTime2 = System.currentTimeMillis();
    }
}
