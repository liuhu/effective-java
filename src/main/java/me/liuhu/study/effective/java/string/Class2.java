package me.liuhu.study.effective.java.string;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019/10/12
 **/
public class Class2 {
    public static String STR = "abc";

    public static void main(String[] args) {
        String str = new StringBuilder("12").append("va").toString();
        System.out.println(str.intern() == str);

        System.out.println("---------------------");
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
