package com.codeartist.component.core.annotation;

import com.codeartist.component.core.entity.enums.Constants;
import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * 项目的根包路径
 *
 * @author J.N.AI
 * @date 2023-11-18
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Value(Constants.ROOT_PACKAGE_KEY)
public @interface RootPackage {
}
