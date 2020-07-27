package me.liuhu.study.practice.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019-05-15 18:00
 **/
public class Test {

    public static void main(String[] args) throws Exception {
        //serializing();
        deserializing();
    }

    private static void serializing() throws Exception {

        Schema schema = new Schema.Parser().parse(new File("src/main/java/me/liuhu/study/effective/java/avro/user.avsc"));

        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("name", "Alyssa");
        user1.put("favorite_number", 256);

        GenericRecord user2 = new GenericData.Record(schema);
        user2.put("name", "Ben");
        user2.put("favorite_number", 7);
        user2.put("favorite_color", "red");



        File file = new File("src/main/java/me/liuhu/study/effective/java/avro/users.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.close();
    }

    private static void deserializing() throws Exception {
        Schema schema = new Schema.Parser().parse(new File("src/main/java/me/liuhu/study/effective/java/avro/user.avsc"));
        File file = new File("src/main/java/me/liuhu/study/effective/java/avro/users.avro");

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
        GenericRecord user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);
        }

    }
}
