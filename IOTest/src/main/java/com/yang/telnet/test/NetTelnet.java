package com.yang.telnet.test;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by yz on 2018/10/15.
 */
public class NetTelnet {

    // Telnet对象
    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    // 提示符。具体请telnet到AIX主机查看
    private char prompt = '$';
    // telnet端口
    private String port;
    // 用户
    private String user;
    // 密码
    private String password;
    // IP地址
    private String ip;
    public NetTelnet() {
        try {
            // AIX主机IP
            this.ip = "192.168.211.129";
            this.password = "123456";
            this.user = "yz";
            this.port = "23";
            telnet.connect(ip, Integer.parseInt(port));
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            // 登录
            readUntil("login:");
            write(user);
            readUntil("Password:");
            write(password);
            readUntil(prompt + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取分析结果
     *
     * @param pattern
     * @return
     */
    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        ch = (char) in.read();
                        sb.append(ch);
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 写
     *
     * @param value
     */
    public void write(String value) {
        try {
            out.println(value);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 向目标发送命令字符串
     *
     * @param command
     * @return
     */
    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 关闭连接
     *
     */
    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            NetTelnet telnet = new NetTelnet();
            // 通过aix的命令“查找主机名称”获取数据
            // 命令是 "hostname"
            // 不熟悉命令的参考<<AIX网络管理手册>>
            String result = telnet.sendCommand("ls");
            System.out.println(result);


            // 最后一定要关闭
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
