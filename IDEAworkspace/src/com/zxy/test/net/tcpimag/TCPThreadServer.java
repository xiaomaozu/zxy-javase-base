package com.zxy.test.net.tcpimag;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPThreadServer {
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(8000);
		while(true){
		//��ȡ��һ���ͻ���,���뿪�����߳�
		Socket socket = server.accept();
		new Thread( new Upload(socket) ).start();
		}
		
		
	}
}
