package me.liuhu.study.effective.java.reflex.oop;

import java.util.Arrays;

/**
 * https://time.geekbang.org/column/article/225596
 *
 * @description:
 * @author: LiuHu
 * @create: 2020/8/18
 **/
public class Test {

    public static void main(String[] args) {
        ChildWrong wrong = new ChildWrong();
        Class<?> wrongClazz = wrong.getClass();
        Arrays.stream(wrongClazz.getMethods())
                .filter(method -> method.getName().equals("setValue")).forEach(method -> {
            try {
                method.invoke(wrong, "test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(wrong.toString());

        System.out.println("-----------------------");


        ChildRight right = new ChildRight();
        Class<?> rightClazz = right.getClass();
        Arrays.stream(rightClazz.getMethods())
                .filter(method -> method.getName().equals("setValue"))
                .filter(method -> !method.isBridge())
                .forEach(method -> {
            try {
                method.invoke(right, "test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(right.toString());
    }
}
