package me.liuhu.study.effective.java.exceptions;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/7/31
 **/
@Slf4j
public class ThreadPoolExceptionTest {
//    static {
//        Thread.setDefaultUncaughtExceptionHandler(
//            (thread, throwable) -> log.error("{} got exception", thread, throwable));
//    }
    public void test() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1,
                new ThreadFactoryBuilder()
                        .setNameFormat("test" + "%d")
                        .setUncaughtExceptionHandler((t, e) -> log.error("{} got exception", t, e))
                        .build());

        // execute 会抛出异常，并且异常的线程会退出
        IntStream.range(1, 7).forEach(i -> pool.execute(() -> {
            if (i == 3) {
                throw new RuntimeException("error");
            }
            log.info("I'm done : {}", i);
        }));

        // submit 异常会吞掉，并且异常的线程不会退出
        List<Future> task = IntStream.range(15, 20).mapToObj(i -> pool.submit(() -> {
            if (i == 18) {
                throw new RuntimeException("error");
            }
            log.info("I'm done : {}", i);
        })).collect(Collectors.toList());

        // 通过 submit 返回的 Future 获取执行状态，会返回异常信息
        /**
         * @see FutureTask#run()
         */
        task.forEach(x -> {
            try {
                log.info("Get result...");
                x.get();
            } catch (Exception e) {
                log.error("Get ERROR", e);
            }
        });

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExceptionTest test = new ThreadPoolExceptionTest();
        test.test();
    }
}
