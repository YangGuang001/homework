package test.synchronizedTest;

/**
 * Created by yz on 2017/5/19.
 */
public class Task {
    private String getData1;
    private String getData2;
    public void doSomething(){
        synchronized (this){
            try {
                System.out.println("begin task");
                Thread.sleep(300);
                String privatedate1 = "长时间处理任务1 threadName=" + Thread.currentThread().getName();
                String privatedate2 = "长时间处理任务2 threadName=" + Thread.currentThread().getName();
                getData1 = privatedate1;
                getData2 = privatedate2;
                System.out.println(getData1);
                System.out.println(getData2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void other(){
        System.out.println("threadname = " + Thread.currentThread().getName() + " "+getData1);
        System.out.println("threadname = " + Thread.currentThread().getName() + " "+getData2);
    }
}
