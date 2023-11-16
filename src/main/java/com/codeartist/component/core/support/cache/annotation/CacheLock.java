package com.codeartist.component.core.support.cache.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author AiJiangnan
 * @date 2019/5/6
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

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

    /**
     * 缓存过期时间
     */
    long timeout();

    /**
     * 缓存过期时间单位
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
