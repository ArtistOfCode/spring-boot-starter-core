package com.codeartist.component.core.code;

import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.exception.BusinessException;

/**
 * 异常处理器接口
 *
 * @author AiJiangnan
 * @date 2023-12-12
 */
public interface ErrorResolver {

    default void rejectClient(MessageCode code) throws BadRequestException {
        throw new BadRequestException(code);
    }

    default void rejectClient(String message, Object... args) throws BadRequestException {
        DefaultMessageCode messageCode = new DefaultMessageCode(
                ApiErrorCode.GLOBAL_CLIENT_ERROR.getCode(), null, message, args);
        rejectClient(messageCode);
    }

    default void reject(MessageCode code) {
        throw new BusinessException(code);
    }

    default void reject(String message, Object... args) throws BadRequestException {
        DefaultMessageCode messageCode = new DefaultMessageCode(
                ApiErrorCode.GLOBAL_BUSINESS_ERROR.getCode(), null, message, args);
        reject(messageCode);
    }
}
