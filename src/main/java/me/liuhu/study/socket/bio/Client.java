package me.liuhu.study.socket.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/5
 **/
@Slf4j
public class Client {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ClientSender(1);
        Thread t2 = new ClientSender(2);

        t1.start();
        Thread.sleep(2000);

        t2.start();
    }

    static class ClientSender extends Thread {
        int i;
        public ClientSender(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            log.info("run {}", i);
            try (Socket socket = new Socket("127.0.0.1",18089);
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                while (true) {
                    log.info("Send BIP to server {}", i);
                    //向服务器端发送一条消息
                    bw.write("BIP\n");
                    bw.flush();

                    String mess = br.readLine();
                    log.info("服务器：{}", mess);

                    Thread.sleep(3000);
                }
            } catch (IOException | InterruptedException e) {
                log.error("", e);
            }
        }
    }
}
