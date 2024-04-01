package com.codeartist.component.core.support.curd;

import com.codeartist.component.core.code.DefaultErrorResolver;
import com.codeartist.component.core.code.ErrorResolver;
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
    private ErrorResolver errorResolver;

    public DefaultEntityContext() {
        this.errorResolver = new DefaultErrorResolver();
    }
}
