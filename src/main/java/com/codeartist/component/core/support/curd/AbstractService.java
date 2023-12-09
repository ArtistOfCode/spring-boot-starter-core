package com.codeartist.component.core.support.curd;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.entity.PageInfo;
import com.codeartist.component.core.entity.PageParam;
import com.codeartist.component.core.entity.event.EntityDeleteEvent;
import com.codeartist.component.core.entity.event.EntitySaveEvent;
import com.codeartist.component.core.entity.event.EntityUpdateEvent;
import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.exception.BusinessException;
import com.codeartist.component.core.support.auth.AuthContext;
import lombok.Getter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.function.Consumer;

/**
 * 抽象服务类
 *
 * @author AiJiangnan
 * @date 2023/6/1
 */
@Getter
public abstract class AbstractService<D, R, P extends PageParam> implements BaseService<R, P> {

    @Autowired
    private BaseMapper<D> mapper;
    @Autowired
    private BaseConverter<D, P, R> converter;
    @Autowired
    private AuthContext authContext;
    @Autowired
    private ObjectProvider<EntityChecker<P, D>> entityCheckers;
    @Autowired
    private ObjectProvider<Consumer<EntityContext<P, D>>> entityContextConsumers;

    protected EntityContext<P, D> createContext() {
        return new DefaultEntityContext<>();
    }

    protected void checkParam(P param) {
        SpringContext.validate(param);
    }

    @Override
    public R get(Long id) {
        D entity = getMapper().selectById(id);
        return getConverter().toVo(entity);
    }

    @Override
    public PageInfo<R> get(P p) {
        D entity = getConverter().toDo(p);

        QueryWrapper<D> wrapper = new QueryWrapper<>(entity);

        IPage<D> page = getMapper().selectPage(p.page(), wrapper);
        return new PageInfo<>(page, getConverter()::toVo);
    }

    @Override
    @Transactional
    public void save(P p) {
        checkParam(p);

        EntityContext<P, D> context = createContext();
        context.setParam(p);

        if (p.getId() != null) {
            update(p, context);
        } else {
            save(p, context);
        }

        clearContext(context);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        D old = getMapper().selectById(id);
        if (old == null) {
            return;
        }

        EntityContext<P, D> context = createContext();
        context.setDelete(true);
        context.setEntity(old);

        checkContext(context);
        consumerContext(context);

        getMapper().deleteById(id);
        SpringContext.publishEvent(new EntityDeleteEvent<>(this, context));

        clearContext(context);
    }

    private void save(P p, EntityContext<P, D> context) {
        Long userId = authContext.getUserId();
        context.setSave(true);

        p.setCreateUser(userId);
        p.setUpdateUser(userId);

        D entity = getConverter().toDo(p);
        context.setEntity(entity);

        checkContext(context);
        consumerContext(context);

        getMapper().insert(entity);
        SpringContext.publishEvent(new EntitySaveEvent<>(this, context));
    }

    private void update(P p, EntityContext<P, D> context) {
        Long userId = authContext.getUserId();
        context.setUpdate(true);

        D old = getMapper().selectById(p.getId());
        context.setOldEntity(old);

        if (old == null) {
            throw new NullPointerException("更新记录不存在");
        }
        p.setUpdateUser(userId);

        D entity = getConverter().toDo(p);
        context.setEntity(entity);

        checkContext(context);
        consumerContext(context);

        getMapper().updateById(entity);
        SpringContext.publishEvent(new EntityUpdateEvent<>(this, context));
    }

    private void checkContext(EntityContext<P, D> context) {
        BindingResult errors = new BeanPropertyBindingResult(context, "context");
        entityCheckers.stream().forEach(checker -> checker.validate(context, errors));
        if (errors.hasFieldErrors()) {
            FieldError fieldError = errors.getFieldError();
            if (fieldError != null) {
                throw new BadRequestException(fieldError.getDefaultMessage());
            }
        }
        if (errors.hasGlobalErrors()) {
            ObjectError globalError = errors.getGlobalError();
            if (globalError != null) {
                throw new BusinessException(globalError.getDefaultMessage());
            }
        }
    }

    private void consumerContext(EntityContext<P, D> context) {
        entityContextConsumers.stream().forEach(consumer -> consumer.accept(context));
    }

    private void clearContext(EntityContext<P, D> context) {
        context.clear();
    }
}
