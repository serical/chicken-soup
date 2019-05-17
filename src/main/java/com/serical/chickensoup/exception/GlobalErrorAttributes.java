package com.serical.chickensoup.exception;

import cn.hutool.core.bean.BeanUtil;
import com.serical.chickensoup.result.Result;
import com.serical.chickensoup.result.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * 全局异常处理
 *
 * @author serical 2019-05-17 19:56:08
 */
@Slf4j
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        ResultEntity error = Result.error();
        Throwable throwable = getError(request);
        if (throwable instanceof GlobalException) {
            GlobalException ex = (GlobalException) throwable;
            error.setStatus(ex.getStatus());
            error.setMessage(ex.getMessage());
        }
        log.error("messageId:{}, error: {}", error.getMessageId(), ExceptionUtils.getStackTrace(throwable));
        return BeanUtil.beanToMap(error);
    }
}