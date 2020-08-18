package me.liuhu.study.effective.java.reflex;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * https://time.geekbang.org/column/article/225596
 *
 * @description:
 * @author: LiuHu
 * @create: 2020/8/18
 **/
@Slf4j
public class JavaReflexTest {

    private void age(int age) {
        log.info("int age = {}", age);
    }

    private void age(Integer age) {
        log.info("Integer age = {}", age);
    }


    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("me.liuhu.study.effective.java.reflex.JavaReflexTest");

//        ReflectionIssueApplication application = new JavaReflexTest.ReflectionIssueApplication();
//        Class<?> clazz = application.getClass();
        Method method = clazz.getDeclaredMethod("age", Integer.class);
        Method method2 = clazz.getDeclaredMethod("age", Integer.TYPE); // 错误的使用方法
        Method method3 = clazz.getDeclaredMethod("age", int.class);

        Object object = clazz.newInstance();

        method.invoke(object, Integer.valueOf("33"));
        method.invoke(object, 33);

        method2.invoke(object, Integer.valueOf("33"));
        method2.invoke(object, 33);

        method3.invoke(object, Integer.valueOf("33"));
        method3.invoke(object, 33);

    }
}
