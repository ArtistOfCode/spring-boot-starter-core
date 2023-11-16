package com.codeartist.component.core.entity;

import com.codeartist.component.core.entity.enums.Constants.EntityEventType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * 实体修改事件
 *
 * @author AiJiangnan
 * @date 2023/6/7
 */
@Setter
@Getter
@JsonIgnoreProperties({"source", "timestamp"})
public class EntityEvent<T> extends ApplicationEvent implements ResolvableTypeProvider {

    private EntityEventType type;
    private T oldEntity;
    private T entity;

    public EntityEvent(Object source) {
        super(source);
    }

    public EntityEvent(Object source, EntityEventType type, T oldEntity, T entity) {
        super(source);
        this.type = type;
        this.oldEntity = oldEntity;
        this.entity = entity;
    }

    @JsonIgnore
    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getEntity()));
    }
}
