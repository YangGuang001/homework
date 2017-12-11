package com.yang.thread.lession4;

import java.util.Random;
import java.util.concurrent.*;

public class SendMessageWork {
    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(1,
            Runtime.getRuntime().availableProcessors(), 60, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(), new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r, "mywork");
                    t.setDaemon(true);
                    return t;
                }
            }, new ThreadPoolExecutor.DiscardPolicy());

    public void sendMessage(final String msg) {
        Runnable runnable = new Runnable() {
            public void run() {
                Random random = new Random();
                int code = random.nextInt(100);
                sendMsg(code, msg);
            }
        };

        EXECUTOR.submit(runnable);
    }

    private void sendMsg(int code, String msg) {
        System.out.printf("code : " + msg);
    }

    public static void main(String[] args) throws Exception {
        SendMessageWork sendMessageWork = new SendMessageWork();
        sendMessageWork.sendMessage("yang");

        TimeUnit.SECONDS.sleep(10);
    }
}
