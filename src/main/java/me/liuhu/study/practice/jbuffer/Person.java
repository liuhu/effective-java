package me.liuhu.study.practice.jbuffer;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import lombok.Data;


/**
 * @description:
 * @author: LiuHu
 * @create: 2019-05-17 14:05
 **/
@Data
public class Person {
    @Protobuf(fieldType = FieldType.STRING, order=1, required = true)
    private String name;
    @Protobuf(fieldType = FieldType.INT32, order=2, required = true)
    private int id;
    @Protobuf(fieldType = FieldType.STRING, order=3, required = false)
    private String email;

    @Protobuf(fieldType = FieldType.DOUBLE, order=4, required = false)
    private Double doubleF;


    @Protobuf(fieldType = FieldType.FLOAT, order=5, required = false)
    private Float floatF;

    @Protobuf(fieldType = FieldType.BYTES, order=6, required = false)
    private byte[] bytesF;

    @Protobuf(fieldType=FieldType.BOOL, order=7, required=false)
    private Boolean boolF;

    @Protobuf(fieldType = FieldType.ENUM, order=8, required=true)
    private SexEnum sex;

}
