package me.liuhu.study.effective.java.clazz;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019/10/23
 **/
public class Test {
    public static void main(String[] args) {
        Test test = new Test();

        // 注意学习class中一些常用的方法
        System.out.println(Test.class.isInstance(test));
    }
}
