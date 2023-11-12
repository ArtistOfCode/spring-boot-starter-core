package com.codeartist.component.core.support.cache.annotation;

import com.codeartist.component.core.entity.enums.Constants;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 缓存注解
 *
 * @author AiJiangnan
 * @since 2018-11-07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    /**
     * 指定不同集群
     */
    String cluster() default Constants.DEFAULT;

    /**
     * 缓存的Key
     */
    @AliasFor("key")
    String value();

    /**
     * 缓存的Key
     */
    @AliasFor("value")
    String key();

    /**
     * 缓存的Key（支持SpEL表达式）
     */
    String spel() default "";

    /**
     * 缓存过期时间
     */
    long timeout() default -1;

    /**
     * 缓存过期时间单位（默认：毫秒）
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
