package com.codeartist.component.core.code;

import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.exception.BusinessException;

import java.util.List;

/**
 * 异常处理器接口
 *
 * @author AiJiangnan
 * @date 2023-12-12
 */
public interface ErrorResolver {

    List<MessageCode> getClientErrors();

    List<MessageCode> getErrors();

    void addClient(MessageCode code);

    void addClient(String defaultMessage, Object... args);

    void add(MessageCode code);

    void add(String defaultMessage, Object... args);

    void rejectClient(MessageCode code) throws BadRequestException;

    void rejectClient(String defaultMessage, Object... args) throws BadRequestException;

    void reject(MessageCode code) throws BusinessException;

    void reject(String defaultMessage, Object... args) throws BusinessException;
}
