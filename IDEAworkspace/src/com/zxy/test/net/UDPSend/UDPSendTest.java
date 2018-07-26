package com.zxy.test.net.UDPSend;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/*
 *  实现UDP协议的发送端:
 *    实现封装数据的类 java.net.DatagramPacket  将你的数据包装
 *    实现数据传输的类 java.net.DatagramSocket  将数据包发出去
 *
 *  实现步骤:
 *    1. 创建DatagramPacket对象,封装数据, 接收的地址和端口
 *    2. 创建DatagramSocket
 *    3. 调用DatagramSocket类方法send,发送数据包
 *    4. 关闭资源
 *
 *    DatagramPacket构造方法:
 *      DatagramPacket(byte[] buf, int length, InetAddress address, int port)
 *
 *    DatagramSocket构造方法:
 *      DatagramSocket()空参数
 *      方法: send(DatagramPacket d)
 *
 */
public class UDPSendTest {

    public static void main(String[] args) throws IOException {

        //创建数据包对象封装要发送的数据，接受端IP，端口

        //DatagramPacket();
        //创建一个InetAddress对象，封装自己的IP地址
        //创建datagramSocket对象
        DatagramSocket ds = new DatagramSocket();
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        Scanner sc=new Scanner(System.in);
        //调用ds的方法，发送数据包
        while(true) {
            String msg=sc.nextLine();
            byte[] date = msg.getBytes();
            DatagramPacket dp = new DatagramPacket(date, date.length, inet, 6000);
            ds.send(dp);

        }
        //关闭资源
      //  ds.close();

    }
}
