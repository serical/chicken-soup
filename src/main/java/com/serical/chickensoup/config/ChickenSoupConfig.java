package com.serical.chickensoup.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义配置
 *
 * @author serical 2019-05-17 19:27:43
 */
@Data
@ConfigurationProperties(prefix = "chicken")
public class ChickenSoupConfig {

    /**
     * slat
     */
    private String hashIdsSalt;
}
