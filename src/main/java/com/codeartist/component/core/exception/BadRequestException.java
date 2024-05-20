package com.codeartist.component.core.exception;

import com.codeartist.component.core.code.ApiErrorCode;
import com.codeartist.component.core.code.MessageCode;
import lombok.Getter;

/**
 * 请求异常，返回客户端异常消息，后端不打印日志
 *
 * @author AiJiangnan
 * @date 2020/9/8
 */
@Getter
public class BadRequestException extends BaseException {

    public BadRequestException(MessageCode messageCode) {
        super(messageCode);
    }

    public BadRequestException(String defaultMessage) {
        super(ApiErrorCode.GLOBAL_CLIENT_ERROR.getCode(), defaultMessage);
    }

    public BadRequestException(String defaultMessage, Object... args) {
        super(ApiErrorCode.GLOBAL_CLIENT_ERROR.getCode(), defaultMessage, args);
    }
}
