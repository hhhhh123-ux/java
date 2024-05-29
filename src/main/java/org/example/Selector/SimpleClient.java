package org.example.Selector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        final String serverHost = "localhost"; // 服务器主机名
        final int serverPort = 8080; // 服务器端口号

        try {
            // 创建 Socket 连接到服务器的 8080 端口
            Socket socket = new Socket(serverHost, serverPort);

            // 获取输入流和输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // 向服务器发送数据
            String message = "Hello, Server!";
            outputStream.write(message.getBytes());
            outputStream.flush();

            // 从服务器接收数据
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            if (bytesRead != -1) {
                String response = new String(buffer, 0, bytesRead);
                System.out.println("服务器响应：" + response);
            }

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
