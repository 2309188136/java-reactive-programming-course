package com.rp.mydemos;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
https://www.bilibili.com/video/BV13e4y1i7SG/?p=3&spm_id_from=333.880.my_history.page.click&vd_source=fcf7e27785f56f99a8022caee7691349
 */
public class NioServerVersion1 {
    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);
        System.out.printf("服务器启动");

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            if (socketChannel != null) {
                System.out.println("链接成功");
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }

            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(6);

                int len = sc.read(byteBuffer);

                if(len > 0) {
                    System.out.println("接受到消息：= " + new String(byteBuffer.array()));
                } else if(len ==-1) {
                    iterator.remove();
                    System.out.println("客户端断开链接");
                }
            }
        }

    }

}
