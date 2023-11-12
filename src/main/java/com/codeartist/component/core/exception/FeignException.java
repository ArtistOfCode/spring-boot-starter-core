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

    private String methodKey;
    private ResponseError responseError;

    public FeignException(String methodKey, ResponseError responseError) {
        super(responseError.getMessage());
        this.methodKey = methodKey;
        this.responseError = responseError;
    }

    public FeignException(String message) {
        super(message);
    }

    public FeignException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignException(Throwable cause) {
        super(cause);
    }

    public FeignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void setResponseError(ResponseError responseError) {
        this.responseError = responseError;
    }
}
