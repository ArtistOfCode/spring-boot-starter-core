package com.codeartist.component.core.entity.event;

import com.codeartist.component.core.support.curd.EntityContext;
import lombok.Getter;
import lombok.Setter;

/**
 * 实体删除事件
 *
 * @author AiJiangnan
 * @date 2023/6/7
 */
@Setter
@Getter
public class EntityDeleteEvent<T> extends EntityEvent<T> {

    public EntityDeleteEvent(Object source, EntityContext<T, ?> entityContext) {
        super(source, entityContext);
    }
}
