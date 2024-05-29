package org.example.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ServerExample {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);
            System.out.println("ServerSocketChannel is open and bound to port 8080");

            while (true) {
                // 非阻塞模式下，accept 方法不会阻塞，会立即返回 SocketChannel 或 null
                SocketChannel socketChannel = serverSocketChannel.accept();

                if (socketChannel != null) {
                    System.out.println("Accepted connection from: " + socketChannel.getRemoteAddress());

                    // 处理连接的业务逻辑
                    // ...

                    // 关闭连接
                    socketChannel.close();
                }

                // 可以进行其他的非阻塞操作
                // ...
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
