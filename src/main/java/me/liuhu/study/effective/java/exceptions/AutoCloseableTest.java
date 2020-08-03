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
            throw new Exception("read error");
        }

        @Override
        public void close() throws Exception {
            throw new Exception("close error");
        }
    }

    public void test() {
        try (TestResource d = new TestResource();
             TestResource d2 = new TestResource()) {
            d.read();
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            log.info("finally ... ");
        }
    }

    public static void main(String[] args) {
        AutoCloseableTest test = new AutoCloseableTest();
        test.test();
    }
}
