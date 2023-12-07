package com.codeartist.component.core.entity.event;

/**
 * 实体修改事件
 *
 * @author AiJiangnan
 * @date 2023/6/7
 */
public class EntitySaveEvent<T> extends EntityEvent<T> {

    public EntitySaveEvent(Object source, T entity) {
        super(source, entity);
        setSave(true);
    }
}