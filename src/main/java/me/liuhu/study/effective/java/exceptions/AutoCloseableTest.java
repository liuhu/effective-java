package me.liuhu.study.effective.java.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/7/31
 **/
@Slf4j
public class AutoCloseableTest {
    public class TestResource implements AutoCloseable {
        public void read() throws Exception {
            log.info("read resource ...");
        }

        @Override
        public void close() throws Exception {
            log.info("close resource ... ");
        }
    }

    public void test() {
        try (TestResource d = new TestResource();
             TestResource d2 = new TestResource()) {
            d.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AutoCloseableTest test = new AutoCloseableTest();
        test.test();
    }
}
