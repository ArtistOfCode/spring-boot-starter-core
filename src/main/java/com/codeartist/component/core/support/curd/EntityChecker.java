package com.codeartist.component.core.support.curd;

/**
 * 实体上下文检查抽象类
 *
 * @author AiJiangnan
 * @date 2023-12-09
 */
public interface EntityChecker<T extends EntityContext<P, D>, P, D> {

    default void checkSave(T context) {
    }

    default void checkUpdate(T context) {
        checkSave(context);
    }

    default void checkDelete(T context) {
    }

    default void checkAll(T context) {
    }

    default void check(T context) {
        checkAll(context);
        if (context.isSave()) {
            checkSave(context);
        } else if (context.isUpdate()) {
            checkUpdate(context);
        } else if (context.isDelete()) {
            checkDelete(context);
        }
    }
}
