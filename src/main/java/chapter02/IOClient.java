package chapter02;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author Chanmoey
 * @date 2022年09月05日
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);  // 创建socket, 并连接到8000端口
                while (true) {
                    try {
                        // 向客服端发送数据
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
                }
            } catch (IOException e) {

            }
        }).start();
    }
}
