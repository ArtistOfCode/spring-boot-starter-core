package com.codeartist.component.core.support.cache.annotation;

import com.codeartist.component.core.entity.enums.Constants;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 清除缓存注解
 *
 * @author AiJiangnan
 * @see Cache
 * @since 2018-11-07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheDelete {

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
}
