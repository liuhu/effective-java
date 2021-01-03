package me.liuhu.study.effective.java.init;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/19
 **/
public class ClazzInitTest1 {

    static class T1 {
        public T1() {
            System.out.println("init T1");
        }
    }

    private final T1 t1 = new T1();

    public static void main(String[] args) {
        ClazzInitTest1 c1 = new ClazzInitTest1();
        System.out.println("init ClazzInitTest1");
    }
}
