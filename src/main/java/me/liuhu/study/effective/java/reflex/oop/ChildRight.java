package me.liuhu.study.effective.java.reflex.oop;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/8/18
 **/
public class ChildRight extends Parent<String> {

    @Override
    public void setValue(String value) {
        System.out.println("Child1.setValue called");
        super.setValue(value);
    }
}
