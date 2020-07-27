package me.liuhu.study.effective.java.clazz;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019/10/23
 **/
public class A {
    class O {
        int n = 1;

        class I {
            int b = n;
        }

        I getAnI(){
            return new I();
        }
    }

    /**
     * 实例 内部非静态类的两种方式
     */
    class M {
        O o = new O();
        O.I i1 = o.getAnI();
        O.I i2 = o.new I();
    }

    class O2 {
        void m(int x) {
            final double z = 0.1;
            double z1 = 0.2;
            class I {
                int y = x;
                double u = z;
                double u1 = z1;
            }
            I i = new I();
        }
    }

    class C {}

    class O3 {
        int m() {
            return new C(){
                int m(){
                    return 1;
                }
            }.m();
        }
    }
}
