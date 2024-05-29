package org.example.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingServer {
    public static void main(String[] args) {
        try (
                Selector selector = Selector.open();
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            // 设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);

            // 绑定监听端口
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));

            // 注册 ServerSocketChannel 到 Selector，关注 ACCEPT 事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server started on port 8080...");

            while (true) {
                // 阻塞等待就绪事件
                int readyChannels = selector.select();

                if (readyChannels == 0) {
                    continue;
                }

                // 获取已选择的键集合
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (key.isAcceptable()) {
                        handleAccept(key, selector);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        // 注册 SocketChannel 到 Selector，关注 READ 事件
        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("Client connected: " + socketChannel.getRemoteAddress());
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int bytesRead = socketChannel.read(buffer);
        if (bytesRead == -1) {
            // Client has disconnected
            System.out.println("Client disconnected: " + socketChannel.getRemoteAddress());
            socketChannel.close();
            return;
        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        String message = new String(data);

        System.out.println("Received from client " + socketChannel.getRemoteAddress() + ": " + message);
    }
}
