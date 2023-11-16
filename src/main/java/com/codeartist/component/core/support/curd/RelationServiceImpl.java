package com.codeartist.component.core.support.curd;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.codeartist.component.core.entity.Rel;
import com.codeartist.component.core.entity.Relation;
import com.codeartist.component.core.SpringContext;
import lombok.Getter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.ResolvableType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 抽象关联服务类
 *
 * @author AiJiangnan
 * @date 2023/6/1
 */
@Getter
public class RelationServiceImpl implements RelationService {

    @Override
    public <T> Relation getRelation(Rel<T> rel, Long id) {
        Set<Long> ids = getMapper(rel.getClazz()).selectObjs(Wrappers.<T>query()
                        .eq(rel.getOne(), id)
                        .select(rel.getMore()))
                .stream().map(o -> (Long) o).collect(Collectors.toSet());
        Relation entity = new Relation();
        entity.setId(id);
        entity.setIds(ids);
        return entity;
    }

    @Override
    @Transactional
    public <T> void saveRelation(Rel<T> rel, Relation param) {
        RelationMapper<T> mapper = getMapper(rel.getClazz());
        mapper.delete(Wrappers.<T>query().eq(rel.getOne(), param.getId()));
        if (!CollectionUtils.isEmpty(param.getIds())) {
            mapper.insertBatch(param);
        }
    }

    @Override
    public <T> void deleteRelation(Rel<T> rel, Long id) {
        RelationMapper<T> mapper = getMapper(rel.getClazz());
        mapper.delete(Wrappers.<T>query().eq(rel.getOne(), id));
    }

    private <T> RelationMapper<T> getMapper(Class<T> entity) {
        ResolvableType requiredType = ResolvableType.forClassWithGenerics(RelationMapper.class, entity);
        ObjectProvider<RelationMapper<T>> beanProvider = SpringContext.getBeanProvider(requiredType);
        return beanProvider.getIfAvailable();
    }
}
