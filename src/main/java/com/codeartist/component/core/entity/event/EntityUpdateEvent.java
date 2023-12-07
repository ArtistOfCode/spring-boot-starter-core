package com.codeartist.component.core.entity.event;

import lombok.Getter;
import lombok.Setter;

/**
 * 实体修改事件
 *
 * @author AiJiangnan
 * @date 2023/6/7
 */
@Setter
@Getter
public class EntityUpdateEvent<T> extends EntityEvent<T> {

    private T oldEntity;

    public EntityUpdateEvent(Object source, T oldEntity, T entity) {
        super(source, entity);
        this.oldEntity = oldEntity;
        setUpdate(true);
    }
}
