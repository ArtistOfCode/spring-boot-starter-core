package com.codeartist.component.core.support.curd;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codeartist.component.core.entity.EntityEvent;
import com.codeartist.component.core.entity.PageInfo;
import com.codeartist.component.core.entity.PageParam;
import com.codeartist.component.core.entity.enums.Constants.EntityEventType;
import com.codeartist.component.core.support.auth.AuthContext;
import com.codeartist.component.core.SpringContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 抽象服务类
 *
 * @author J.N.AI
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

    protected void doBasicCheck(P p) {
        SpringContext.validate(p);
    }

    protected void doBusinessCheck(D old, P p) {
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
        doBasicCheck(p);

        Long userId = authContext.getUserId();

        if (p.getId() != null) {
            D old = getMapper().selectById(p.getId());
            if (old == null) {
                throw new NullPointerException("更新记录不存在");
            }
            p.setUpdateUser(userId);
            doBusinessCheck(old, p);

            D entity = getConverter().toDo(p);
            getMapper().updateById(entity);
            SpringContext.publishEvent(new EntityEvent<>(this, EntityEventType.UPDATE, old, entity));
        } else {
            p.setCreateUser(userId);
            p.setUpdateUser(userId);
            doBusinessCheck(null, p);

            D entity = getConverter().toDo(p);
            getMapper().insert(entity);
            SpringContext.publishEvent(new EntityEvent<>(this, EntityEventType.SAVE, null, entity));
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        D old = getMapper().selectById(id);
        if (old == null) {
            return;
        }

        getMapper().deleteById(id);
        SpringContext.publishEvent(new EntityEvent<>(this, EntityEventType.DELETE, old, old));
    }
}
