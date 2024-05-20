package com.codeartist.component.core.exception;

import com.codeartist.component.core.code.ApiErrorCode;
import com.codeartist.component.core.code.MessageCode;
import lombok.Getter;

/**
 * 业务异常，返回客户端异常消息，warn 级别日志
 *
 * @author AiJiangnan
 * @date 2020/9/8
 */
@Getter
public class BusinessException extends BaseException {

    public BusinessException(MessageCode messageCode) {
        super(messageCode);
    }

    public BusinessException(String defaultMessage) {
        super(ApiErrorCode.GLOBAL_BUSINESS_ERROR.getCode(), defaultMessage);
    }

    public BusinessException(String defaultMessage, Object... args) {
        super(ApiErrorCode.GLOBAL_BUSINESS_ERROR.getCode(), defaultMessage, args);
    }
}
