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

    void rejectClient(MessageCode code) throws BadRequestException;

    void rejectClient(String message, Object... args) throws BadRequestException;

    void reject(MessageCode code) throws BusinessException;
}
