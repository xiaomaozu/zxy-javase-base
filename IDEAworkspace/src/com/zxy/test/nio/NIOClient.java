package com.zxy.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {

    private  int flag=1;
    //    private int port=8088;
    private static int blockSize=4096;
    //分配空间大小，发送数据的缓存区
    private static ByteBuffer sendbuffer=ByteBuffer.allocate(blockSize);
    //接送数据的缓存区
    private static ByteBuffer receivebuffer=ByteBuffer.allocate(blockSize);
    //选择器
    private final static InetSocketAddress serverAddress=new InetSocketAddress("127.0.0.1",7080);

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        //打开选择器
        Selector selector=Selector.open();
        socketChannel.register(selector,SelectionKey.OP_CONNECT);
        socketChannel.connect(serverAddress);

        Set<SelectionKey> selectionKeys;
        Iterator<SelectionKey> iterator;
        SelectionKey selectionKey;
        SocketChannel client;

        String receiveTest;
        String sendTest;

        while(true){
            selectionKeys=selector.selectedKeys();
            iterator=selectionKeys.iterator();
            while(iterator.hasNext()){
                selectionKey=iterator.next();
                if(selectionKey.isConnectable()){
                    System.out.println("客户端开始发起连接了");
                    client=(SocketChannel) selectionKey.channel();
                    if(client.isConnectionPending()){
                        client.finishConnect();
                        System.out.println("客户端完成连接操作");
                        sendbuffer.clear();
                        sendbuffer.put("你好，server".getBytes());
                        sendbuffer.flip();
                        client.write(sendbuffer);
//                        if()
                    }
                }
                if(selectionKey.isReadable()){
                    client=(SocketChannel)selectionKey.channel();
                    receivebuffer.clear();
                   // count=client.read(receivebuffer);
                }
            }
        }
    }
}
