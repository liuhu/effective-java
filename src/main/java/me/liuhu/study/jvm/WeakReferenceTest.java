package me.liuhu.study.jvm;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/12
 **/
public class WeakReferenceTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("12");
        WeakReference<BigDecimal> weak = new WeakReference<>(bigDecimal);

        System.out.println(weak.get());

        //干掉强引用，确保这个实例只有userWeak的弱引用
        bigDecimal = null;

        //进行一次GC垃圾回收
        System.gc();
        System.out.println("After gc");
        System.out.println(weak.get());
    }
}
