package me.liuhu.study.effective.java.exceptions;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/3
 **/
public class ExceptionTable {
    public void test() {
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("123");
        }
    }
}
