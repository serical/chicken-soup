package com.serical.chickensoup.controller;

import com.serical.chickensoup.config.CacheKeys;
import com.serical.chickensoup.result.Result;
import com.serical.chickensoup.result.ResultEntity;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * api接口
 *
 * @author serical 2019-05-17 19:27:51
 */
@RestController
public class ChickenSoupController {

    private final ReactiveStringRedisTemplate redisTemplate;

    public ChickenSoupController(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/")
    public Mono<ResultEntity> root() {
        return redisTemplate.opsForSet().randomMember(CacheKeys.getFunKeys()).map(Result::success);
    }

    @GetMapping("/chicken")
    public Mono<ResultEntity> chicken() {
        return redisTemplate.opsForSet().randomMember(CacheKeys.getFunKeys()).map(Result::success);
    }
}
