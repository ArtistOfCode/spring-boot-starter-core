package com.codeartist.component.core.support.curd;

import com.codeartist.component.core.entity.Rel;
import com.codeartist.component.core.entity.Relation;

/**
 * 关联表操作接口
 *
 * @author 艾江南
 * @date 2023/4/23
 */
public interface RelationService {

    <T> Relation getRelation(Rel<T> rel, Long id);

    <T> void saveRelation(Rel<T> rel, Relation param);

    <T> void deleteRelation(Rel<T> rel, Long id);
}
