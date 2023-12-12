package com.codeartist.component.core.entity;

/**
 * 数据变更操作人参数接口
 *
 * @author AiJiangnan
 * @date 2023/6/1
 */
public interface UpdateParam {

    default void setCreateUser(Long id) {
    }

    default void setUpdateUser(Long id) {
    }
}
