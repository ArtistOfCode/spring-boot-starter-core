package com.codeartist.component.core.exception;

import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.code.MessageCode;
import lombok.Getter;

/**
 * 业务异常，返回客户端异常消息，warn 级别日志
 *
 * @author AiJiangnan
 * @date 2020/9/8
 */
@Getter
public class BusinessException extends RuntimeException {

    private MessageCode messageCode;

    public BusinessException() {
        super();
    }

    public BusinessException(MessageCode messageCode) {
        super(SpringContext.getMessage(messageCode));
        this.messageCode = messageCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
