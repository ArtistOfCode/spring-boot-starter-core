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
     * Caffeine：指定本地缓存Bean
     * Redis：指定不同集群
     */
    String[] name() default {Constants.DEFAULT, Constants.DEFAULT};

    @AliasFor("key")
    String value() default "";

    /**
     * 缓存的Key（支持SpEL表达式）
     */
    @AliasFor("value")
    String key() default "";

    /**
     * 缓存过期时间
     */
    long timeout() default -1;

    /**
     * 缓存过期时间单位（默认：秒）
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 本地缓存
     */
    boolean local() default false;

    /**
     * 二级缓存，和name相关，默认数组第一个为LocalCache，第二个为Cache
     */
    boolean combine() default false;
}
