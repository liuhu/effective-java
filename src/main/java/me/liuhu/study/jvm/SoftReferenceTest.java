package me.liuhu.study.jvm;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * -Xms20m -Xmx20m  -Xmn10m -XX:+PrintGCDetails
 * -XX:PretenureSizeThreshold=2m
 * -XX:+UseSerialGC
 *
 * @description:
 * @author: LiuHu
 * @create: 2020/8/12
 **/
public class SoftReferenceTest {
    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal("12");
        SoftReference<BigDecimal> softReference = new SoftReference<>(decimal);
        System.out.println(softReference.get());

        // 需要手动解除强引用
        decimal = null;

        System.gc();//
        System.out.println("After gc get soft reference -- " + softReference.get());

        //往堆中填充数据，导致OOM
        List<byte[]> list = new LinkedList<>();
        try {
            for (int i = 0; i < 100; i++) {
                list.add(new byte[1024 * 1024 * 1]); //1M的对象
                System.out.println("Padding 1M -- " + softReference.get());
            }
        } catch (Throwable e) {
            System.out.println("Exception -- " + softReference.get());
            e.printStackTrace();
        }

        System.out.println("Get -- " + softReference.get());
    }
}
