package com.codeartist.component.core.support.cache.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 清除缓存注解
 *
 * @author AiJiangnan
 * @see LocalCache
 * @since 2018-11-07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LocalCacheDelete {

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
