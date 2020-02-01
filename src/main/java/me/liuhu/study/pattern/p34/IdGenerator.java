package me.liuhu.study.pattern.p34;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/1/31
 **/
public interface IdGenerator {

    String generate() throws IdGenerationFailureException;
}
