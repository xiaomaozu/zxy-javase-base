package com.zxy.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

     private  int flag=1;
//    private int port=8088;
    private int blockSize=4096;
    //分配空间大小，发送数据的缓存区
    private ByteBuffer sendbuffer=ByteBuffer.allocate(blockSize);
    //接送数据的缓存区
    private ByteBuffer receivebuffer=ByteBuffer.allocate(blockSize);
    //选择器
    private Selector selector;

    public NIOServer(int port) throws IOException {
        //打开服务端的channel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //是否堵塞
        serverSocketChannel.configureBlocking(false);
        //获得一个socket对象
        ServerSocket serverSocket= serverSocketChannel.socket();
        //绑定IP、端口
        serverSocket.bind(new InetSocketAddress(port));
        //打开选择器
        selector=Selector.open();
        //注册
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        System.out.println("Server start"+port);
    }

    //监听，不断的监听
    public void listen() throws IOException{
        while(true){
            selector.select();
            //返回的是一个集合
            Set<SelectionKey> selectionkeys=selector.selectedKeys();
            //遍历它
            Iterator<SelectionKey> iterator=selectionkeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey=iterator.next();
                iterator.remove();
                //业务逻缉

            }
        }
    }

    private  void handleKey(SelectionKey selectionKey) throws IOException{
        ServerSocketChannel server=null;
        SocketChannel client=null;
        String receiveText;
        String sendText;
        int count=0;
        if(selectionKey.isAcceptable()){
            server=(ServerSocketChannel) selectionKey.channel();
            client =server.accept();
            client.configureBlocking(false);
            client.register(selector,selectionKey.OP_READ);
        }else if(selectionKey.isReadable()){
            client=(SocketChannel) selectionKey.channel();
            count=client.read(receivebuffer);
            if(count>0){
                receiveText=new String(receivebuffer.array(),0,count);
                System.out.println("服务端接收到客户端的信息:"+receiveText);
                //注册写的事件
                client.register(selector,selectionKey.OP_WRITE);
            }
        }else if(selectionKey.isWritable()){

            sendbuffer.clear();
            client=(SocketChannel)selectionKey.channel();
            //发送的数据
            sendText="msg send to to client"+flag++;
            //写到我们的缓冲区里面去
            sendbuffer.put(sendText.getBytes());
            //Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式。
            sendbuffer.flip();
            client.write(sendbuffer);

            System.out.println("服务端发送数据给客户端："+sendText);

        }
    }

    public static void main(String[] args) throws IOException{
        int port=7080;
        NIOServer server=new NIOServer(port);
        server.listen();

    }
}
