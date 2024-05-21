package com.codeartist.component.core.support.curd;

import java.util.function.Consumer;

/**
 * 实体上下文保存前处理器
 *
 * @author AiJiangnan
 * @date 2023-12-09
 */
public interface EntityConsumer<T extends EntityContext<P, D>, P, D> extends Consumer<T> {

    default void acceptAll(T context) {
    }

    default void acceptSave(T context) {
    }

    default void acceptUpdate(T context) {
        acceptSave(context);
    }

    default void acceptDelete(T context) {
    }

    default void afterAll(T context) {
    }

    default void afterSave(T context) {
    }

    default void afterUpdate(T context) {
        afterSave(context);
    }

    default void afterDelete(T context) {
    }

    @Override
    default void accept(T context) {
        acceptAll(context);
        if (context.isSave()) {
            acceptSave(context);
        } else if (context.isUpdate()) {
            acceptUpdate(context);
        } else if (context.isDelete()) {
            acceptDelete(context);
        }
    }

    default void after(T context) {
        afterAll(context);
        if (context.isSave()) {
            afterSave(context);
        } else if (context.isUpdate()) {
            afterUpdate(context);
        } else if (context.isDelete()) {
            afterDelete(context);
        }
    }
}
