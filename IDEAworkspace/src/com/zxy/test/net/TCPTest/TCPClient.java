package com.zxy.test.net.TCPTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 *  实现TCP客户端,连接到服务器
 *  和服务器实现数据交换
 *  实现TCP客户端程序的类 java.net.Socket
 *
 *  构造方法:
 *      Socket(String host, int port)  传递服务器IP和端口号
 *      注意:构造方法只要运行,就会和服务器进行连接,连接失败,抛出异常
 *
 *    OutputStream  getOutputStream() 返回套接字的输出流
 *      作用: 将数据输出,输出到服务器
 *
 *    InputStream getInputStream() 返回套接字的输入流
 *      作用: 从服务器端读取数据
 *
 *    客户端服务器数据交换,必须使用套接字对象Socket中的获取的IO流,自己new流,不行
 */
public class TCPClient {

    public static void main(String[] args) throws IOException {
        //创建一个socket对象用于连接服务器
        Socket socket=new Socket("127.0.0.1",8888);
        //获取字符输出流，将对象写向服务器
        OutputStream out=socket.getOutputStream();

        out.write("服务器".getBytes());

        InputStream in=socket.getInputStream();
        byte[] data=new byte[1024];
        in.read(data);
        int len=data.length;
        System.out.println(new String(data,0,len));

        socket.close();

    }

}
