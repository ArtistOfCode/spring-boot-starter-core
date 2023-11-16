package com.codeartist.component.core.support.captcha.annotation;

import com.codeartist.component.core.support.captcha.CaptchaType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 验证码注解
 *
 * @author AiJiangnan
 * @date 2023-11-12
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Captcha {

    /**
     * 验证码类型
     */
    @AliasFor("type")
    CaptchaType value() default CaptchaType.PICTURE;

    /**
     * 验证码类型
     */
    @AliasFor("value")
    CaptchaType type() default CaptchaType.PICTURE;

    /**
     * 业务码
     */
    String code();
}
