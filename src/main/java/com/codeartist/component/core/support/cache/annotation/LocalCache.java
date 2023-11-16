package com.codeartist.component.core.support.cache.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 本地缓存
 *
 * @author AiJiangnan
 * @since 2023-04-23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LocalCache {

    /**
     * 缓存的Key
     */
    @AliasFor("key")
    String value() default "";

    /**
     * 缓存的Key
     */
    @AliasFor("value")
    String key() default "";

    /**
     * 缓存的Key（支持SpEL表达式）
     */
    String spel() default "";
}
