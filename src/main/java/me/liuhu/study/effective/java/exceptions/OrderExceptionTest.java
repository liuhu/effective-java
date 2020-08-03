package me.liuhu.study.effective.java.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/3
 **/
@Slf4j
public class OrderExceptionTest {

    public class BisException extends RuntimeException {
        public BisException(String msg) {
            super(msg);
        }
    }

    private Integer test(int i) {
        try {
            return bisProcess(i);
        } catch (BisException e) {
            log.error("cache error", e);
            if (i == 3 || i == 4) {
                throw new RuntimeException("BisException -- case " + i);
            }
        } finally {
            log.info("finally... ");
            if (i == 4) {
                throw new RuntimeException("BisException -- case 4");
            }
        }
        return 5;
    }

    private Integer bisProcess(int i) {
        if (i == 1) {
            throw new RuntimeException("RuntimeException -- case 1");
        }
        if (i == 2) {
            throw new BisException("BisException -- case 2");
        }
        if (i == 3) {
            throw  new BisException("BisException -- case 3");
        }
        if (i == 4) {
            throw  new BisException("BisException -- case 4");
        }
        return i;
    }


    public static void main(String[] args) {
        OrderExceptionTest test = new OrderExceptionTest();

        // case i = 1, try 抛出异常，catch 没有捕获
        // case i = 2, try 抛出异常，catch 捕获了
        // case i = 3, try 抛出异常，catch 捕获了，但又抛出异常
        // case i = 4, try 抛出异常，catch 捕获了，但又抛出异常，finally 又抛出异常
        System.out.println(test.test(8));
        System.out.println("finish");
    }
}
