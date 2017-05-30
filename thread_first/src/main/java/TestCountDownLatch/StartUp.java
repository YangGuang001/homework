package TestCountDownLatch;

/**
 * Created by yz on 2017/5/30.
 */
public class StartUp {
    public static void main(String[] args) {
        if (ApplicationStartUpDemo.getInstance().serviceStartUp()){
            System.out.println("服务启动成功!!!");
        }else {
            System.out.println("服务启动失败!!!");
        }
    }
}
