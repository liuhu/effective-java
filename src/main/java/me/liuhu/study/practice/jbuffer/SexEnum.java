package me.liuhu.study.practice.jbuffer;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019-05-17 14:10
 **/
public enum SexEnum implements EnumReadable {

    /**
     * 男
     */
    MALE(1),
    /**
     * 女
     */
    FEMALE(2);

    private final int value;


    SexEnum(int value) { this.value = value; }

    public int toValue() { return this.value; }

    /* (non-Javadoc)
     * @see com.baidu.bjf.remoting.protobuf.Enumable#value()
     */
    @Override
    public int value() {
        return toValue();
    }

}
