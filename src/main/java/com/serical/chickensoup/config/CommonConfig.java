package com.serical.chickensoup.config;

import lombok.extern.slf4j.Slf4j;
import org.hashids.Hashids;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebFilter;

/**
 * 注册公共bean
 *
 * @author serical 2019-05-17 19:27:22
 */
@Slf4j
@Component
public class CommonConfig {

    @Bean
    Hashids hashids(ChickenSoupConfig config) {
        return new Hashids(config.getHashIdsSalt());
    }

    @Bean
    public ReactiveStringRedisTemplate reactiveRedisTemplateString
            (ReactiveRedisConnectionFactory connectionFactory) {
        final StringRedisSerializer serializer = new StringRedisSerializer();
        final RedisSerializationContext<String, String> context =
                RedisSerializationContext.<String, String>newSerializationContext()
                        .key(serializer)
                        .value(serializer)
                        .hashKey(serializer)
                        .hashValue(serializer).build();


        log.info(context.getKeySerializationPair().toString());
        log.info((context.getValueSerializationPair().toString()));
        log.info((context.getHashKeySerializationPair().toString()));
        log.info((context.getHashValueSerializationPair().toString()));
        return new ReactiveStringRedisTemplate(connectionFactory, context);
    }

    @Bean
    public WebFilter contextPathWebFilter(ServerProperties serverProperties) {
        String contextPath = serverProperties.getServlet().getContextPath();
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (request.getURI().getPath().startsWith(contextPath)) {
                return chain.filter(
                        exchange.mutate()
                                .request(request.mutate().contextPath(contextPath).build())
                                .build());
            }
            return chain.filter(exchange);
        };
    }
}
