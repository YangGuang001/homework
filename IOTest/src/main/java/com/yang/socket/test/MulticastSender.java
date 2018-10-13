package com.yang.socket.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by yz on 2018/10/6.
 */
public class MulticastSender {
    private int port;
    private String host;

    public MulticastSender(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void send(String data) {
        try {
            InetAddress address = InetAddress.getByName(this.host);
            DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, address, this.port);
            MulticastSocket multicastSocket = new MulticastSocket();
            multicastSocket.send(packet);
            multicastSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8888;
        String host = "224.0.0.1";
        MulticastSender sender = new MulticastSender(port, host);
        sender.send("广播协议");
    }
}
