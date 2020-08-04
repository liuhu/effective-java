package me.liuhu.study.effective.java.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/4
 **/
public class LinkedHashMapTest {

    /**
     * 需要打开 accessOrder 开关，实现LRU，可查看如下代码
     * @see LinkedHashMap#afterNodeAccess(HashMap.Node)
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<>(10, 0.75f, true);
        map.put(3, 3);
        map.put(1, 1);
        map.put(5, 5);
        map.put(2, 2);
        map.put(4, 4);

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey());
        }

        System.out.println();

        map.get(1);
        map.get(2);
        map.get(3);
        map.get(4);
        map.get(5);
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey());
        }
    }
}
