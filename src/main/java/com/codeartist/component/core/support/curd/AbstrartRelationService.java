package com.codeartist.component.core.support.curd;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.entity.Relation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 抽象关联服务类
 *
 * @author AiJiangnan
 * @date 2023-12-10
 */
@Getter
public abstract class AbstrartRelationService<D> implements RelationService {

    @Autowired
    private RelationMapper<D> mapper;

    private final SFunction<D, Long> one;
    private final SFunction<D, Long> more;

    protected AbstrartRelationService(SFunction<D, Long> one, SFunction<D, Long> more) {
        this.one = one;
        this.more = more;
    }

    @Override
    public Relation get(Long id, boolean column) {
        checkId(id);

        Set<Long> ids = getMapper().selectObjs(Wrappers.<D>lambdaQuery()
                        .eq(column, one, id)
                        .eq(!column, more, id)
                        .select(column ? more : one))
                .stream().map(o -> (Long) o).collect(Collectors.toSet());

        Relation entity = new Relation();
        entity.setId(id);
        entity.setIds(ids);
        return entity;
    }

    @Override
    public void save(Relation param, boolean column) {
        checkParam(param);
        delete(param.getId(), column);
        getMapper().insertBatch(param, column);
    }

    @Override
    public void delete(Long id, boolean column) {
        checkId(id);

        getMapper().delete(Wrappers.<D>lambdaQuery()
                .eq(column, one, id)
                .eq(!column, more, id));
    }

    private void checkId(Long id) {
        Assert.notNull(id, "Id must not null.");
    }

    private void checkParam(Relation param) {
        SpringContext.validate(param);
    }
}
