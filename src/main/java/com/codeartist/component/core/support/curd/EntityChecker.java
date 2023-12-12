package com.codeartist.component.core.support.curd;

/**
 * 实体上下文检查抽象类
 *
 * @author AiJiangnan
 * @date 2023-12-09
 */
public abstract class EntityChecker<P, D> {

    protected void checkSave(EntityContext<P, D> context) {
    }

    protected void checkUpdate(EntityContext<P, D> context) {
        checkSave(context);
    }

    protected void checkDelete(EntityContext<P, D> context) {
    }

    protected void checkAll(EntityContext<P, D> context) {
    }

    public void check(EntityContext<P, D> context) {
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
