package test.synchronizedTest;


/**
 * Created by yz on 2017/5/19.
 */
public class Run {
    public static void main(String[] args) {
        Task task = new Task();
        MtThread1 mythread = new MtThread1(task);
        mythread.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyThread2 myThread2 = new MyThread2(task);
        myThread2.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        long begintime = CommonUtils.beginTime1;
        if (CommonUtils.beginTime1 > CommonUtils.beginTime2){
            begintime = CommonUtils.beginTime2;
        }

        long endtime = CommonUtils.endTime1;
        if (CommonUtils.endTime2 > CommonUtils.endTime1){
            endtime = CommonUtils.endTime2;
        }

        System.out.println("耗时: " + (endtime - begintime) / 1000);
    }
}
