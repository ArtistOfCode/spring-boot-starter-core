package com.codeartist.component.core.entity.event;

import com.codeartist.component.core.support.curd.EntityContext;

/**
 * 实体保存事件
 *
 * @author AiJiangnan
 * @date 2023/6/7
 */
public class EntitySaveEvent<T> extends EntityEvent<T> {

    public EntitySaveEvent(Object source, EntityContext<T, ?> entityContext) {
        super(source, entityContext);
    }
}