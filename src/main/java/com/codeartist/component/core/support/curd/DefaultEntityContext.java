package com.codeartist.component.core.support.curd;

import com.codeartist.component.core.code.ApiErrorCode;
import com.codeartist.component.core.code.DefaultMessageCode;
import com.codeartist.component.core.code.MessageCode;
import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.exception.BusinessException;
import lombok.Data;

/**
 * 实体操作上下文默认实现
 *
 * @author AiJiangnan
 * @date 2023-12-09
 */
@Data
public class DefaultEntityContext<P, D> implements EntityContext<P, D> {

    private boolean save;
    private boolean update;
    private boolean delete;
    private P param;
    private D entity;
    private D oldEntity;

    @Override
    public void clear() {
        setSave(false);
        setUpdate(false);
        setDelete(false);
        setParam(null);
        setEntity(null);
        setOldEntity(null);
    }

    @Override
    public void rejectClient(MessageCode code) throws BadRequestException {
        throw new BadRequestException(code);
    }

    @Override
    public void rejectClient(String message, Object... args) throws BadRequestException {
        DefaultMessageCode messageCode = new DefaultMessageCode(ApiErrorCode.GLOBAL_CLIENT_ERROR.getCode(),
                null, message, args);
        rejectClient(messageCode);
    }

    @Override
    public void reject(MessageCode code) {
        throw new BusinessException(code);
    }

    @Override
    public void reject(String message, Object... args) throws BadRequestException {
        DefaultMessageCode messageCode = new DefaultMessageCode(ApiErrorCode.GLOBAL_BUSINESS_ERROR.getCode(),
                null, message, args);
        reject(messageCode);
    }
}
