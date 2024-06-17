package com.codeartist.component.core.exception;

import com.codeartist.component.core.code.ApiErrorCode;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindingResult;

/**
 * 请求异常，返回客户端异常消息，后端不打印日志
 *
 * @author AiJiangnan
 * @date 2020/9/8
 */
public class BadRequestException extends BaseException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(BindingResult bindingResult) {
        super(bindingResult);
    }

    public static BadRequestException of(MessageSourceResolvable resolvable) {
        BadRequestException exception = new BadRequestException();
        String errorCode = ApiErrorCode.GLOBAL_CLIENT_ERROR.name();
        if (resolvable.getCodes() != null && resolvable.getCodes().length > 0) {
            errorCode = resolvable.getCodes()[0];
        }
        exception.reject(errorCode, resolvable.getArguments(), resolvable.getDefaultMessage());
        return exception;
    }

    public static BadRequestException of(String defaultMessage) {
        return of(ApiErrorCode.GLOBAL_CLIENT_ERROR.name(), defaultMessage);
    }

    public static BadRequestException of(String code, String defaultMessage) {
        return of(code, null, defaultMessage);
    }


    public static BadRequestException of(String code, Object[] args, String defaultMessage) {
        BadRequestException exception = new BadRequestException();
        exception.reject(code, args, defaultMessage);
        return exception;
    }
}
