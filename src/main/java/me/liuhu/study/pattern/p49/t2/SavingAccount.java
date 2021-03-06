package me.liuhu.study.pattern.p49.t2;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/3/9
 **/
@Slf4j
public class SavingAccount implements IAccount {

    private int balance;

    public SavingAccount(int balance) {
        this.balance = balance;
    }

    @Override
    public int getBalance() {
        log.info("储蓄账户余额为 {}", balance);
        return balance;
    }
}
