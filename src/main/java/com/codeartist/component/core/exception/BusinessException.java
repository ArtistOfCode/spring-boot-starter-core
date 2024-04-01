package com.codeartist.component.core.exception;

import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.code.ApiErrorCode;
import com.codeartist.component.core.code.DefaultMessageCode;
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

    private final MessageCode messageCode;

    public BusinessException(MessageCode messageCode) {
        super(SpringContext.getMessage(messageCode));
        this.messageCode = messageCode;
    }

    public BusinessException(String defaultMessage) {
        this(new DefaultMessageCode(ApiErrorCode.GLOBAL_BUSINESS_ERROR.getCode(), defaultMessage));
    }

    public BusinessException(String defaultMessage, Object... args) {
        this(new DefaultMessageCode(ApiErrorCode.GLOBAL_BUSINESS_ERROR.getCode(), defaultMessage, args));
    }
}
