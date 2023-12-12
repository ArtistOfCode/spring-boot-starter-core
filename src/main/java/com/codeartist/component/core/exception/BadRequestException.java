package com.codeartist.component.core.exception;

import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.code.MessageCode;
import lombok.Getter;

/**
 * 请求异常，返回客户端异常消息，后端不打印日志
 *
 * @author AiJiangnan
 * @date 2020/9/8
 */
@Getter
public class BadRequestException extends RuntimeException {

    private MessageCode messageCode;

    public BadRequestException() {
        super();
    }

    public BadRequestException(MessageCode messageCode) {
        super(SpringContext.getMessage(messageCode));
        this.messageCode = messageCode;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    protected BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
