package com.codeartist.component.core.exception;

import com.codeartist.component.core.code.ApiErrorCode;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindingResult;

/**
 * 业务异常，返回客户端异常消息，warn 级别日志
 *
 * @author AiJiangnan
 * @date 2020/9/8
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super();
    }

    public BusinessException(BindingResult bindingResult) {
        super(bindingResult);
    }

    public static BusinessException of(MessageSourceResolvable resolvable) {
        BusinessException exception = new BusinessException();
        String errorCode = ApiErrorCode.GLOBAL_BUSINESS_ERROR.name();
        if (resolvable.getCodes() != null && resolvable.getCodes().length > 0) {
            errorCode = resolvable.getCodes()[0];
        }
        exception.reject(errorCode, resolvable.getArguments(), resolvable.getDefaultMessage());
        return exception;
    }

    public static BusinessException of(String defaultMessage) {
        return of(ApiErrorCode.GLOBAL_BUSINESS_ERROR.name(), defaultMessage);
    }

    public static BusinessException of(String code, String defaultMessage) {
        return of(code, null, defaultMessage);
    }

    public static BusinessException of(String code, Object[] args, String defaultMessage) {
        BusinessException exception = new BusinessException();
        exception.reject(code, defaultMessage);
        return exception;
    }
}
