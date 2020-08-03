package me.liuhu.study.effective.java.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/3
 **/
@Slf4j
public class StaticExceptionTest {
    public static Exception ORDER_EXISTS = new Exception("订单已经存在");
    public static Exception orderExists() {
        return new Exception("订单已经存在");
    }

    private void wrong() {
        try {
            throw ORDER_EXISTS;
        } catch (Exception e) {
            log.error("createOrderWrong", e);
        }
        try {
            throw ORDER_EXISTS;
        } catch (Exception e) {
            log.error("cancelOrderWrong", e);
        }
    }

    private void right() {
        try {
            throw orderExists();
        } catch (Exception e) {
            log.error("createOrderWrong", e);
        }
        try {
            throw orderExists();
        } catch (Exception e) {
            log.error("cancelOrderWrong", e);
        }
    }

    public static void main(String[] args) {
        StaticExceptionTest test = new StaticExceptionTest();
        test.wrong();
        test.right();
    }
}
