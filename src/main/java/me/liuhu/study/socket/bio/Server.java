package me.liuhu.study.socket.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/5
 **/
@Slf4j
public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {
        try(ServerSocket ss = new ServerSocket(18089)) {
            log.info("服务端启动成功， port {}", ss.getLocalPort());
            while (true) {
                Socket socket = ss.accept();
                Thread thread = new Thread(new Worker(socket));
                thread.start();
            }
        } catch (IOException e) {
            log.error("连接异常", e);
        }
    }

    static class Worker implements Runnable {
        Socket socket;
        public Worker(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                while (true) {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    // 阻塞的，等待"\n"
                    String message = br.readLine();
                    log.info("收到消息 {}", message);
                    if("BIP".equals(message)) {
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        bw.write("OK" + "\n");
                        bw.flush();
                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                log.error("连接异常", e);
            }
        }
    }
}
