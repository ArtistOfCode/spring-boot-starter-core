package com.codeartist.component.core.support.captcha.annotation;

import java.lang.annotation.*;

/**
 * 验证码Key
 *
 * @author AiJiangnan
 * @date 2023-11-12
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaptchaCode {
}
