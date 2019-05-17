package com.serical.chickensoup.enums;

import lombok.Getter;

/**
 * 响应体枚举
 *
 * @author serical 2019-03-23 10:09:10
 */
@Getter
public enum ResultEnums {

    SUCCESS(0, ""),
    ERROR(-1, "系统错误");

    private int key;
    private String value;

    ResultEnums(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
