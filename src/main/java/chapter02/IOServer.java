package chapter02;

import com.alibaba.fastjson.parser.JSONToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Chanmoey
 * @date 2022年09月05日
 */
public class IOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);  // 服务端创建Socket, 监听8000端口

        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();  // 阻塞方法, 接收客户端连接, socket表示一个客户端连接

                    new Thread(() -> {  // 每收到一个连接就创建一个线程, 专门负责该连接的数据读取
                        try {
                            int len;
                            byte[] data = new byte[1024];  // 每次最大读取1024个字节

                            // 读取客户端数据
                            InputStream inputStream = socket.getInputStream();
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
