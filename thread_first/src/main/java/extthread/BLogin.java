package extthread;

import controller.LoginServlet;

/**
 * Created by yz on 2017/5/17.
 */
public class BLogin extends Thread {
    @Override
    public void run(){
        LoginServlet servlet = new LoginServlet();
        servlet.doPost("b","bb");
    }
}
