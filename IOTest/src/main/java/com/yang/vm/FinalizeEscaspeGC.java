package com.yang.vm;

/**
 * Created by yz on 2017/7/9.
 */
public class FinalizeEscaspeGC {

    public static FinalizeEscaspeGC save_hook = null;

    public void isAlive(){
        System.out.println("yes, i am still alive. :)");
    }

    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscaspeGC.save_hook = this;
    }

    public static void main(String[] args) throws Throwable{
        save_hook = new FinalizeEscaspeGC();

        save_hook = null;

        System.gc();

        Thread.sleep(500);

        if (save_hook != null){
            save_hook.isAlive();
        }else {
            System.out.println("no, i am dead. :(");
        }

        save_hook = null;

        System.gc();

        Thread.sleep(500);

        if (save_hook != null){
            save_hook.isAlive();
        }else {
            System.out.println("no, i am dead. :(");
        }
    }
}
