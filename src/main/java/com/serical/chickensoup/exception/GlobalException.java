package com.serical.chickensoup.exception;

import com.serical.chickensoup.enums.ResultEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目自定义异常
 *
 * @author serical 2019-05-17 19:35:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalException extends RuntimeException {

    private int status;
    private String msg;

    public GlobalException(ResultEnums status) {
        super(status.getValue());
        this.status = status.getKey();
        this.msg = status.getValue();
    }
}
