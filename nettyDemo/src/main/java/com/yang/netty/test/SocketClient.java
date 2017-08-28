package com.yang.netty.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by yz on 2017/8/7.
 */
public class SocketClient {
    public static void main(String[] args) {
        User user = new User("yang", 18);
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            Socket socket = new Socket("127.0.0.1",9873);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(user);
            out.flush();

            in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            User user1 = (User) in.readObject();
            if (user1 != null) {
                System.out.println("用户名: " + user1.getUsername() + "年龄: " + user1.getAge());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null){
                    out.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
