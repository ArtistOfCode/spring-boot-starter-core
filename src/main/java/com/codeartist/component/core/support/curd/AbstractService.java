package com.codeartist.component.core.support.curd;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 抽象服务类
 *
 * @author AiJiangnan
 * @date 2023/6/1
 */
@Slf4j
@Getter
public abstract class AbstractService<D, R, P extends PageParam> implements BaseService<R, P> {

    @Autowired
    private BaseMapper<D> mapper;
    @Autowired
    private BaseConverter<D, P, R> converter;
    @Autowired
    private AuthContext authContext;
    @Autowired
    private ObjectProvider<EntityChecker<EntityContext<P, D>, P, D>> entityCheckers;
    @Autowired
    private ObjectProvider<EntityConsumer<EntityContext<P, D>, P, D>> entityContextConsumers;

    protected EntityContext<P, D> createContext() {
        return new DefaultEntityContext<>();
    }

    protected void checkParam(P param) {
        SpringContext.validate(param);
    }

    @Override
    public R get(Long id) {
        Assert.notNull(id, "ID不能为空");

        D entity = getMapper().selectById(id);
        return getConverter().toVo(entity);
    }

    @Override
    public PageInfo<R> get(P p) {
        D entity = getConverter().toDo(p);

        QueryWrapper<D> wrapper = Wrappers.query(entity)
                .orderBy(p.getOrderBy() != null, p.getAsc(), p.getOrderBy());

        IPage<D> page = getMapper().selectPage(p.page(), wrapper);
        return new PageInfo<>(page, getConverter()::toVo);
    }

    @Override
    public void save(P p) {
        checkParam(p);

        EntityContext<P, D> context = createContext();
        context.setParam(p);

        if (p.getId() != null) {
            doUpdate(p, context);
        } else {
            doSave(p, context);
        }

        doFinally(context);
    }

    @Override
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
        afterContext(context);

        SpringContext.publishEvent(new EntityDeleteEvent<>(this, context));

        doFinally(context);
    }

    private void doSave(P p, EntityContext<P, D> context) {
        Long userId = authContext.getUserId();
        context.setSave(true);

        p.setCreateUser(userId);
        p.setUpdateUser(userId);

        checkContext(context);

        D entity = getConverter().toDo(p);
        context.setEntity(entity);
        consumerContext(context);
        getMapper().insert(entity);
        afterContext(context);

        SpringContext.publishEvent(new EntitySaveEvent<>(this, context));
    }

    private void doUpdate(P p, EntityContext<P, D> context) {
        Long userId = authContext.getUserId();
        context.setUpdate(true);

        D old = getMapper().selectById(p.getId());
        context.setOldEntity(old);

        if (old == null) {
            throw BadRequestException.of("更新记录不存在");
        }
        p.setUpdateUser(userId);

        checkContext(context);

        D entity = getConverter().toDo(p);
        context.setEntity(entity);
        consumerContext(context);
        getMapper().updateById(entity);
        afterContext(context);

        SpringContext.publishEvent(new EntityUpdateEvent<>(this, context));
    }

    private void doFinally(EntityContext<P, D> context) {
        context.clear();

        List<ObjectError> allErrors = context.getErrors().getAllErrors();

        if (!CollectionUtils.isEmpty(allErrors)) {
            throw new BusinessException(context.getErrors());
        }
    }

    private void checkContext(EntityContext<P, D> context) {
        StopWatch stopWatch = new StopWatch("Check Context Time");
        try {
            entityCheckers.stream().forEach(checker -> {
                stopWatch.start(checker.getClass().getSimpleName());
                try {
                    checker.check(context);
                } finally {
                    stopWatch.stop();
                }
            });
        } finally {
            if (log.isDebugEnabled()) {
                if (stopWatch.getTaskCount() > 0) {
                    log.debug("Check context:\n{}", stopWatch.prettyPrint());
                }
            }
        }
    }

    private void consumerContext(EntityContext<P, D> context) {
        StopWatch stopWatch = new StopWatch("Consumer Context Time");
        try {
            entityContextConsumers.stream().forEach(consumer -> {
                stopWatch.start(consumer.getClass().getSimpleName());
                try {
                    consumer.accept(context);
                } finally {
                    stopWatch.stop();
                }
            });
        } finally {
            if (log.isDebugEnabled()) {
                if (stopWatch.getTaskCount() > 0) {
                    log.debug("Consumer context:\n{}", stopWatch.prettyPrint());
                }
            }
        }
    }

    private void afterContext(EntityContext<P, D> context) {
        StopWatch stopWatch = new StopWatch("After Context Time");
        try {
            entityContextConsumers.stream().forEach(consumer -> {
                stopWatch.start(consumer.getClass().getSimpleName());
                try {
                    consumer.after(context);
                } finally {
                    stopWatch.stop();
                }
            });
        } finally {
            if (log.isDebugEnabled()) {
                if (stopWatch.getTaskCount() > 0) {
                    log.debug("After context:\n{}", stopWatch.prettyPrint());
                }
            }
        }
    }
}
