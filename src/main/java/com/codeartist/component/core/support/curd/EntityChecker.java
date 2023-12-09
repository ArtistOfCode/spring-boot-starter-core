package com.codeartist.component.core.support.curd;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author AiJiangnan
 * @date 2023-12-09
 */
public abstract class EntityChecker<P, D> implements Validator {

    protected void checkSave(EntityContext<P, D> context, Errors errors) {
    }

    protected void checkUpdate(EntityContext<P, D> context, Errors errors) {
        checkSave(context, errors);
    }

    protected void checkDelete(EntityContext<P, D> context, Errors errors) {
    }

    protected void checkAll(EntityContext<P, D> context, Errors errors) {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(EntityContext.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object target, Errors errors) {
        EntityContext<P, D> context = (EntityContext<P, D>) target;
        checkAll(context, errors);
        if (context.isSave()) {
            checkSave(context, errors);
        } else if (context.isUpdate()) {
            checkUpdate(context, errors);
        } else if (context.isDelete()) {
            checkDelete(context, errors);
        }
    }
}
