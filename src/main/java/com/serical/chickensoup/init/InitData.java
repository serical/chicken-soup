package com.serical.chickensoup.init;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ArrayUtil;
import com.google.common.collect.Lists;
import com.serical.chickensoup.config.CacheKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 数据初始化
 *
 * @author serical 2019-05-17 19:56:25
 */
@Slf4j
@Component
public class InitData implements CommandLineRunner {


    private final ReactiveStringRedisTemplate redisTemplate;

    public InitData(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // clear
        final String keys = CacheKeys.getFunKeys();
        redisTemplate.opsForSet().delete(CacheKeys.getFunKeys()).subscribe((result) -> log.info("delete result {}", result));

        // init
        try (InputStream inputStream = InitData.class.getClassLoader().getResourceAsStream("from-nows-fun.txt")) {
            List<String> funList = Lists.newArrayList();
            IoUtil.readUtf8Lines(inputStream, funList);
            redisTemplate.opsForSet().add(keys, ArrayUtil.toArray(funList, String.class))
                    .subscribe((result) -> log.info("init {} chickenSoup", result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
