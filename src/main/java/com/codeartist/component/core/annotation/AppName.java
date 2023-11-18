package com.codeartist.component.core.annotation;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * 应用名称/服务名称
 *
 * @author J.N.AI
 * @date 2023-11-18
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Value("${spring.application.name}")
public @interface AppName {
}
