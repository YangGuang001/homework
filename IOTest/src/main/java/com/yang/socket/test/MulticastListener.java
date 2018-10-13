package com.yang.socket.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by yz on 2018/10/6.
 */
public class MulticastListener {
    private int port;
    private String host;

    public MulticastListener(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void listen() {
        byte[] data = new byte[1024];

        try {
            InetAddress address = InetAddress.getByName(this.host);
            MulticastSocket multicastSocket = new MulticastSocket(this.port);
            multicastSocket.joinGroup(address);

            DatagramPacket packet = new DatagramPacket(data, data.length);
            multicastSocket.receive(packet);
            String message = new String(data, 0, packet.getLength());
            System.out.println(message);
            multicastSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 1234;
        String host = "224.0.0.1";
        MulticastListener listener = new MulticastListener(port, host);
        listener.listen();
    }
}
