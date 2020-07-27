package me.liuhu.study.practice.jbuffer;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019-05-17 14:08
 **/
public class Test {
    public static void main(String[] args) throws Exception{
        serializing();
        deserializing();

        System.out.println(CodedConstant.getDescriptor(AddressBookProtosPOJO.class).toProto());

    }

    private static void serializing() {
        Codec<AddressBookProtosPOJO> simpleTypeCodec = ProtobufProxy
                .create(AddressBookProtosPOJO.class, true);

        AddressBookProtosPOJO adderss = new AddressBookProtosPOJO();

        Person person = new Person();
        person.setName("小王");
        person.setId(1);
        person.setEmail("a@a.com");
        person.setDoubleF(1.00222);
        person.setFloatF(Float.valueOf("1"));
        person.setBytesF("ewerew你好".getBytes());
        person.setBoolF(true);
        person.setSex(SexEnum.FEMALE);
        adderss.setPerson(person);
        adderss.setPersons(Arrays.asList(person));
        adderss.setStringList(Arrays.asList("a","b"));
        adderss.setIntList(Arrays.asList(1,2,2,3,3));

        try {
            // 序列化
            byte[] bb = simpleTypeCodec.encode(adderss);

            // Write the new address book back to disk.
            FileOutputStream output = new FileOutputStream("src/main/java/me/liuhu/study/effective/java/jbuffer/addressBookProtos.bin");
            output.write(bb);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializing() throws Exception {
        Codec<AddressBookProtosPOJO> simpleTypeCodec = ProtobufProxy
                .create(AddressBookProtosPOJO.class);

        File directory = new File("");
        String courseFile = directory.getCanonicalPath() ;

        File file = new File("src/main/java/me/liuhu/study/effective/java/jbuffer/addressBookProtos.bin");
        byte[] fileContent = Files.readAllBytes(file.toPath());
        AddressBookProtosPOJO sss =  simpleTypeCodec.decode(fileContent);
        System.out.println(sss);
        System.out.println(new String(sss.getPerson().getBytesF(), StandardCharsets.UTF_8));

        BigDecimal.ZERO.toString();
    }


}
