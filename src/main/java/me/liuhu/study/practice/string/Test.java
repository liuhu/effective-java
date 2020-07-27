package me.liuhu.study.practice.string;

import me.liuhu.study.practice.string.inner.Class3;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019/10/12
 **/
public class Test {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = new String("abc").intern();

        BiFunction<String, String, Boolean> checkEqual = (s1, s2) -> s1 == s2;



        // str1 为常量池中对象地址， str2 为堆中对象地址,但实际会引用char[]数组
        System.out.print("1---- ");
        System.out.println(checkEqual.apply(str1, str2));

        //  str3 常量池中对象地址
        System.out.print("2---- ");
        System.out.println(checkEqual.apply(str2, str3));

        System.out.print("3---- ");
        System.out.println(checkEqual.apply(str1, str3));

        // 在同包不同类下
        System.out.print("4---- ");
        System.out.println(str1 == InnerClass.STR);

        // 在同包不同类下
        System.out.print("5---- ");
        System.out.println(str1 == Class2.STR);

        // 在不同包不同类下
        System.out.print("6---- ");
        System.out.println(str1 == Class3.STR);

        // 从静态方法中返回
        System.out.print("7---- ");
        System.out.println(str1 == Class3.getStr());

        // 从普通方法中返回
        System.out.print("8---- ");
        System.out.println(str1 == new Class3().getStr2());

        // 4～8 的例子都是true，说明java8中 常量池是全局共享的吗？


        // s2 获取的是常量池中的引用
        String s1 = new String("1") + new String("1");
        s1.intern();
        String s2 = "11";
        System.out.print("9---- ");
        System.out.println(s1 == s2);



        Long a = 5L;
        Long b = 5L;
        // in cache
        System.out.println(a == b);

        Long c = 129L;
        Long d = 129L;
        // no in cache
        System.out.println(c == d);

        List<Long> nums = Arrays.asList(129L, 130L);
        System.out.println(getNum(nums) == nums.get(0));
    }

    private static Long getNum(List<Long> nums) {
        return nums.get(0);
    }

    public class InnerClass {
        public static final String STR = "abc";
    }
}
