package com.codeartist.component.core.exception;

/**
 * Throwable包装类
 *
 * @author AiJiangnan
 * @date 2023-11-13
 */
public class ThrowableWrapper extends RuntimeException {

    private final Throwable original;

    public ThrowableWrapper(Throwable original) {
        super(original.getMessage(), original);
        this.original = original;
    }

    public Throwable getOriginal() {
        return this.original;
    }
}
