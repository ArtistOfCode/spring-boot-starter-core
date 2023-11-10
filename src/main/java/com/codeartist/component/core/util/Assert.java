package com.codeartist.component.core.util;


import com.codeartist.component.core.entity.ICode;
import com.codeartist.component.core.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 业务断言
 *
 * @author 艾江南
 * @date 2020/9/8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Assert {

    public static void state(boolean expression, String message) {
        if (expression) {
            error(message);
        }
    }

    public static void state(boolean expression, ICode code) {
        if (expression) {
            error(code);
        }
    }

    public static void state(boolean expression, Supplier<? extends RuntimeException> exception) {
        if (expression) {
            throw exception.get();
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            error(message);
        }
    }

    public static void notNull(Object object, ICode code) {
        if (object == null) {
            error(code);
        }
    }

    public static void notNull(Object object, Supplier<? extends RuntimeException> exception) {
        if (object == null) {
            throw exception.get();
        }
    }

    public static void notEmpty(Collection<?> object, String message) {
        if (object == null || object.isEmpty()) {
            error(message);
        }
    }

    public static void notEmpty(Collection<?> object, ICode code) {
        if (object == null || object.isEmpty()) {
            error(code);
        }
    }

    public static void notEmpty(Collection<?> object, Supplier<? extends RuntimeException> exception) {
        if (object == null || object.isEmpty()) {
            throw exception.get();
        }
    }

    public static void isEmpty(Collection<?> object, String message) {
        if (object != null && !object.isEmpty()) {
            error(message);
        }
    }

    public static void isEmpty(Collection<?> object, ICode code) {
        if (object != null && !object.isEmpty()) {
            error(code);
        }
    }

    public static void isEmpty(Collection<?> object, Supplier<? extends RuntimeException> exception) {
        if (object != null && !object.isEmpty()) {
            throw exception.get();
        }
    }

    public static void notBlank(String string, String message) {
        if (string == null || string.trim().isEmpty()) {
            error(message);
        }
    }

    public static void notBlank(String string, ICode code) {
        if (string == null || string.trim().isEmpty()) {
            error(code);
        }
    }

    public static void notBlank(String string, Supplier<? extends RuntimeException> exception) {
        if (string == null || string.trim().isEmpty()) {
            throw exception.get();
        }
    }

    private static void error(String message) {
        throw new BusinessException(message);
    }

    private static void error(ICode code) {
        throw new BusinessException(code);
    }
}
