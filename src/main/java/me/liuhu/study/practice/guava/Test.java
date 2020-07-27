package me.liuhu.study.practice.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;


/**
 * @description:
 * @author: LiuHu
 * @create: 2019-05-13 11:36
 **/
@Slf4j
public class Test {

    private Cache<String, CacheDto> cache = CacheBuilder.newBuilder()
            .build();

    public Cache<String, CacheDto> getCache() {
        return cache;
    }

    public static void main(String[] args) {
        Test test = new Test();
        Cache<String, CacheDto> dtoCache = test.getCache();
        CacheDto c1 = new CacheDto();
        c1.setId("1");
        dtoCache.put(c1.getId(), c1);

        log.info("{}", dtoCache.getIfPresent(c1.getId()));

        c1.setName("name");

        new Thread(() -> {
            c1.setName("sdfdfdfd");
            log.info("-----");
        }).start();

        new Thread(() -> {
            log.info("{}", dtoCache.getIfPresent(c1.getId()));
        }).start();

        log.info("{}", dtoCache.getIfPresent(c1.getId()));

        CacheDto sss = dtoCache.getIfPresent(c1.getId());
        sss.setName("撒旦发射的");
        log.info("{}", dtoCache.getIfPresent(c1.getId()));

    }
}
