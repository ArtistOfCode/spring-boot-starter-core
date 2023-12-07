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
public class EntityDeleteEvent<T> extends EntityEvent<T> {

    public EntityDeleteEvent(Object source, T entity) {
        super(source, entity);
        setDelete(true);
    }
}
