package com.zxy.test.net;


import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 *  表示互联网中的IP地址
 *    java.net.InetAddress
 *  静态方法
 *    static InetAddress  getLocalHost()   LocalHost本地主机
 *    返回本地主机,返回值InetAddress对象
 *
 *    static InetAddress getByName(String hostName)传递主机名,获取IP地址对象
 *
 *  非静态方法
 *     String getHoustAddress()获取主机IP地址
 *     String getHoustName()获取主机名
 *
 */
public class InnetAddressDemo {

    /**
     * static InetAddress getLocalHost  返回本地主机
     * @param args
     */
    public static void main(String[] args) throws UnknownHostException {

        //输出结果就是主机名加Ip地址
     /*   InetAddress inet=InetAddress.getLocalHost();
        System.out.println(inet);
        System.out.println(inet.getAddress());
        System.out.println(inet.getHostName());
        System.out.println(inet.getHostAddress());*/

        function_1();

    }

    /*
     * static InetAddress getByName(String hostName)传递主机名,获取IP地址对象
     */
    public static void function_1()throws UnknownHostException {
        InetAddress inet = InetAddress.getByName("www.baidu.com");
        System.out.println(inet);
    }

}
