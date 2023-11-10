package com.codeartist.component.core.support.cache.annotation;

import java.lang.annotation.*;

/**
 * 本地缓存
 *
 * @author 艾江南
 * @since 2023-04-23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LocalCache {

    /**
     * 缓的Key
     */
    String key();

    /**
     * 缓的Key（支持SpEL表达式）
     */
    String spel() default "";
}
