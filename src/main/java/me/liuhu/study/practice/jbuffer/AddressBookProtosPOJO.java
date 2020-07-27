package me.liuhu.study.practice.jbuffer;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019-05-17 14:04
 **/
@Data
public class AddressBookProtosPOJO {

    @Protobuf(fieldType = FieldType.OBJECT, order=1, required = false)
    private Person person;

    @Protobuf(fieldType = FieldType.OBJECT, order=2, required = false)
    private List<Person> persons;

    @Protobuf(fieldType = FieldType.STRING, order=3, required = false)
    private List<String> stringList;

    @Protobuf(fieldType = FieldType.INT32, order=4, required = false)
    private List<Integer> intList;
}
