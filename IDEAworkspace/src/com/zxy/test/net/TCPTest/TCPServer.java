package com.zxy.test.net.TCPTest;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
        *  实现TCP服务器程序
        *  表示服务器程序的类 java.net.TCPServer
        *  构造方法:
        *    TCPServer(int port) 传递端口号
        *
        *  很重要的事情: 必须要获得客户端的套接字对象Socket
        *    Socket  accept()
        */
public class TCPServer {


    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8888);
        //调用服务器套接字对象中的方法accept() 获取客户端套接字对象
        Socket socket = server.accept();
        //通过客户端套接字对象,socket获取字节输入流,读取的是客户端发送来的数据
//        System.out.println(socket);
        InputStream in=socket.getInputStream();
        byte[] data=new byte[1024];
        int len=in.read(data);
        System.out.println(new String(data,0,len));

        OutputStream outputStream=socket.getOutputStream();
        outputStream.write("收到了".getBytes());
        socket.close();
        server.close();
    }

}
