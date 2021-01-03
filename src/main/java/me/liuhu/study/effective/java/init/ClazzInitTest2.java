package me.liuhu.study.effective.java.init;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/19
 **/
public class ClazzInitTest2 {
    public static class Parent {
        public static String parentStr = "parent static string";

        static {
            System.out.println("parent static fields");
            System.out.println(parentStr);
        }

        public Parent() {
            System.out.println("parent instance initialization");
        }
    }

    public static class Sub extends Parent {
        public static String subStr = "sub static string";

        static {
            System.out.println("sub static fields");
            System.out.println(subStr);
        }

        public Sub() {
            System.out.println("sub instance initialization");
        }

        public static void main(String[] args) {
            System.out.println("sub main");
            new Sub();
        }
    }
}
