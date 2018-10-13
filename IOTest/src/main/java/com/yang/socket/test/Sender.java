package com.yang.socket.test;

import java.io.IOException;
import java.net.*;

/**
 * Created by yz on 2018/10/6.
 */
public class Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket sendSocket = new DatagramSocket();

        int port = 8888;

        InetAddress ip = InetAddress.getLocalHost();

        String string = "你好，接收到消息";

        byte[] buf = string.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, ip, port);

        sendSocket.send(sendPacket);

        byte[] getBuf = new byte[1024];

        DatagramPacket getPacket = new DatagramPacket(getBuf, getBuf.length);

        sendSocket.receive(getPacket);

        String str = new String(getBuf, 0, getPacket.getLength());
        System.out.println("接收端IP:" + getPacket.getAddress().getHostAddress());
        System.out.println("接收端port:" + getPacket.getPort());
        System.out.println("接收到消息:" + str);
    }
}
