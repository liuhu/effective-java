package me.liuhu.study.pattern.p29;

import java.util.UUID;

/**
 * @description:
 * @author: LiuHu
 * @create: 2020/1/27
 **/
public class WalletRpcService implements IWalletRpcService {
    public String moveMoney(String id, Long buyerId, Long sellerId, Double amount) {
        return UUID.randomUUID().toString();
    }
}
