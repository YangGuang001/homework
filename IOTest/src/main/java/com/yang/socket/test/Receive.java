package com.yang.socket.test;

import java.io.IOException;
import java.net.*;

/**
 * Created by yz on 2018/10/6.
 */
public class Receive {
    public static void main(String[] args) throws IOException {
        int port = 8888;
        InetAddress ip = InetAddress.getLocalHost();
        DatagramSocket sendSocket = new DatagramSocket(port, ip);


        //接收报文
        byte[] getBuf = new byte[1024];

        DatagramPacket getPacket = new DatagramPacket(getBuf, getBuf.length);

        sendSocket.receive(getPacket);

        String str = new String(getBuf, 0, getPacket.getLength());
        System.out.println("对端的IP:" + getPacket.getAddress().getHostAddress());
        System.out.println("对端的port:" + getPacket.getPort());
        System.out.println("接收到消息:" + str);

        //发送报文

        String string = "你好，相应消息";
        byte[] buf = string.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, getPacket.getSocketAddress());

        sendSocket.send(sendPacket);

        sendSocket.close();
    }
}
