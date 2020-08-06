package me.liuhu.study.socket.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/5
 **/
@Slf4j
public class Server {

    ByteBuffer readBuffer = ByteBuffer.allocate(256);
    ByteBuffer writeBuffer = ByteBuffer.allocate(256);


    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {
        try {
            // step1: 创建 selector
            // Selector 是 Channel 对象的多路复用器
            Selector selector = Selector.open();

            // step2: 创建 channel 通道
            ServerSocketChannel channel = ServerSocketChannel.open();
            // step3: 绑定短裤
            channel.bind(new InetSocketAddress(18089));
            // step4: 设置为非阻塞模式
            channel.configureBlocking(false);
            // step5: 将服务端的监听 channel 注册到 Selector, 监听连接消息
            channel.register(selector, SelectionKey.OP_ACCEPT);

            // step6: 循环等待新接入的连接
            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0) continue;

                // 获取可用 channel 集合
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    // step7: 根据连接状态处理业务
                    if (key.isAcceptable()) {
                        acceptHandler(key, selector);
                    }
                    if (key.isReadable()) {
                        readHandler(key, selector);
                    }
                    if (key.isWritable()) {
                        // TODO;
                    }

                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接入事件处理
     * @param key
     * @param selector
     * @throws IOException
     */
    private void acceptHandler(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        // 接受此通道套接字的连接，是非阻塞的
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        // 将客户端 channel 再注册到 selector 中, 监听读事件
        clientChannel.register(selector, SelectionKey.OP_READ);
        // 向客户端返回 OK
        clientChannel.write(StandardCharsets.UTF_8.encode("OK"));
        log.info("客户端连接 {} ", clientChannel.getRemoteAddress());
    }

    /**
     * 处理读事件
     * @param key
     */
    private void readHandler(SelectionKey key, Selector selector) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        readBuffer.clear();
        StringBuilder request = new StringBuilder();
        try {
            while (clientChannel.read(readBuffer) > 0) {
                readBuffer.flip();
                request.append(StandardCharsets.UTF_8.decode(readBuffer));
            }
            log.info("读取 client 数据 {}", request.toString());
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            log.error("channel closed", e);
            key.cancel();
            clientChannel.close();
            return;
        }
        clientChannel.write(StandardCharsets.UTF_8.encode("成功读取消息: " + request));
        clientChannel.register(selector, SelectionKey.OP_READ);
    }
}
