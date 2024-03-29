package com.codeartist.component.core.entity.event;

import com.codeartist.component.core.support.curd.EntityContext;
import lombok.Getter;
import lombok.Setter;

/**
 * 实体更新事件
 *
 * @author AiJiangnan
 * @date 2023/6/7
 */
@Setter
@Getter
public class EntityUpdateEvent<T> extends EntityEvent<T> {
    public EntityUpdateEvent(Object source, EntityContext<?, T> entityContext) {
        super(source, entityContext);
    }
}
