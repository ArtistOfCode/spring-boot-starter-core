package com.codeartist.component.core.exception;

import com.codeartist.component.core.entity.ResponseError;
import lombok.Getter;

/**
 * Feign调用异常，返回客户端异常消息，error 级别日志
 *
 * @author AiJiangnan
 * @date 2022/7/27
 */
@Getter
public class FeignException extends RuntimeException {

    private final String methodKey;
    private final ResponseError responseError;

    public FeignException(String methodKey, ResponseError responseError) {
        super(responseError.getMessage());
        this.methodKey = methodKey;
        this.responseError = responseError;
    }
}
