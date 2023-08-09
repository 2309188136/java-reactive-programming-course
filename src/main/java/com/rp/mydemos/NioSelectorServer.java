package com.rp.mydemos;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
use putty telnet as test client
cmd telnet localhost 9000
https://www.bilibili.com/video/BV13e4y1i7SG/?p=3&spm_id_from=333.880.my_history.page.click&vd_source=fcf7e27785f56f99a8022caee7691349
 */
public class NioSelectorServer {

    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();

        //ServerSocketChannel 注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

        while (!Thread.currentThread().isInterrupted()) {

            selector.select(); //没有 IO　事件，会堵塞；等待IO　事件发生
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();
                // 如果 OP_ACCEPT事件， 则进行链接获取事件注册
                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 这里只注册了读事件， 如果需要给客户短发送数据可以注册写事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端链接成功");
                } else if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(6);
                    int len = sc.read(byteBuffer);
                    if(len > 0) {
                        System.out.println("接受到消息：= " + new String(byteBuffer.array()));
                    } else if(len ==-1) {
                        System.out.println("客户端断开链接");
                        sc.close();
                    }
                }
            }
            //从事件集合里删除本次处理的 key, 防止 下次 select 重复处理
            iterator.remove();
        }

    }
}
