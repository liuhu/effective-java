package me.liuhu.study.effective.java.exceptions;


import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/7/27
 **/
@Slf4j
public class FinallyOverwriteTest {

    public void wrong() {
        try {
            log.info("try..");
            //异常丢失
            throw new RuntimeException("try");
        } finally {
            log.info("finally..");
            throw new RuntimeException("finally");
        }
    }

    public void right() {
        try {
            log.info("try..");
            throw new RuntimeException("try");
        } finally {
            log.info("finally..");
            try {
                throw new RuntimeException("finally");
            } catch (Exception e) {
                log.info("finally", e);
            }
        }
    }

    public void right2() throws Exception {
        Exception ex = null;
        try {
            log.info("try..");
            throw new RuntimeException("try");
        } catch (Exception e) {
            ex = e;
        } finally {
            log.info("finally..");
            try {
                throw new RuntimeException("finally");
            } catch (Exception e) {
                if (null != ex) {
                    ex.addSuppressed(e);
                } else {
                    ex = e;
                    log.info("finally", e);
                }

            }
        }
        throw ex;
    }

    public static void main(String[] args) {
        FinallyOverwriteTest test = new FinallyOverwriteTest();
        try {
            test.wrong();
        } catch (Exception e) {
            log.error("wrong...", e);
        }

        log.info("------------------------------------");
        log.info("------------------------------------");

        try {
            test.right();
        } catch (Exception e) {
            log.error("right...", e);
        }

        log.info("------------------------------------");
        log.info("------------------------------------");

        try {
            test.right2();
        } catch (Exception e) {
            log.error("right2...", e);
        }
    }
}
