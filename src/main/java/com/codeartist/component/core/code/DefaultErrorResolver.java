package com.codeartist.component.core.code;

import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.exception.BusinessException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常默认实现
 *
 * @author AiJiangnan
 * @date 2024/4/1
 */
@Getter
public class DefaultErrorResolver implements ErrorResolver {

    private List<MessageCode> clientErrors;
    private List<MessageCode> errors;

    @Override
    public void addClient(MessageCode code) {
        if (clientErrors == null) {
            clientErrors = new ArrayList<>();
        }
        clientErrors.add(code);
    }

    @Override
    public void addClient(String defaultMessage, Object... args) {
        addClient(new DefaultMessageCode(ApiErrorCode.GLOBAL_CLIENT_ERROR.getCode(), defaultMessage, args));
    }

    @Override
    public void add(MessageCode code) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(code);
    }

    @Override
    public void add(String defaultMessage, Object... args) {
        add(new DefaultMessageCode(ApiErrorCode.GLOBAL_BUSINESS_ERROR.getCode(), defaultMessage, args));
    }

    @Override
    public void rejectClient(MessageCode code) throws BadRequestException {
        throw new BadRequestException(code);
    }

    @Override
    public void rejectClient(String defaultMessage, Object... args) throws BadRequestException {
        throw new BadRequestException(defaultMessage, args);
    }

    @Override
    public void reject(MessageCode code) throws BusinessException {
        throw new BusinessException(code);
    }

    @Override
    public void reject(String defaultMessage, Object... args) throws BusinessException {
        throw new BusinessException(defaultMessage, args);
    }
}
