package me.liuhu.study.effective.java.account;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LiuHu
 * @create: 2019-05-07 22:52
 **/
@Slf4j
@Data
public class Account2 {
    // actr 应该为单例
    private Allocator2 actr;
    private int balance;
    private String name;

    public Account2(Allocator2 actr, int balance, String name) {
        this.actr = actr;
        this.balance = balance;
        this.name = name;
    }

    // 转账
    void transfer(Account2 target, int amt){
        // 一次性申请转出账户和转入账户，直到成功
        actr.apply(this, target);

        try{
            // 锁定转出账户
            synchronized(this){
                // 锁定转入账户
                synchronized(target){
                    if (this.balance > amt){
                        Thread.sleep(3000);
                        this.balance -= amt;
                        target.balance += amt;
                        log.info("转账完成 {} -> {}", this.name, target.name);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            actr.free(this, target);
        }
    }
}
