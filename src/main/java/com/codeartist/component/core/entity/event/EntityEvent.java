package com.codeartist.component.core.entity.event;

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
abstract class EntityEvent<T> extends ApplicationEvent implements ResolvableTypeProvider {

    private boolean save;
    private boolean update;
    private boolean delete;

    private T entity;

    public EntityEvent(Object source, T entity) {
        super(source);
        this.entity = entity;
    }

    @JsonIgnore
    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getEntity()));
    }
}
