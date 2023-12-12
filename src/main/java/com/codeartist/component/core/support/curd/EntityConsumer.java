package com.codeartist.component.core.support.curd;

import java.util.function.Consumer;

/**
 * 实体上下文保存前处理器
 *
 * @author AiJiangnan
 * @date 2023-12-09
 */
public abstract class EntityConsumer<P, D> implements Consumer<EntityContext<P, D>> {

    protected void acceptAll(EntityContext<P, D> context) {
    }

    protected void acceptSave(EntityContext<P, D> context) {
    }

    protected void acceptUpdate(EntityContext<P, D> context) {
        acceptSave(context);
    }

    protected void acceptDelete(EntityContext<P, D> context) {
    }

    @Override
    public void accept(EntityContext<P, D> context) {
        acceptAll(context);
        if (context.isSave()) {
            acceptSave(context);
        } else if (context.isUpdate()) {
            acceptUpdate(context);
        } else if (context.isDelete()) {
            acceptDelete(context);
        }
    }
}
