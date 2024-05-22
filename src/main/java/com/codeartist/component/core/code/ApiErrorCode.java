package com.codeartist.component.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举
 *
 * @author AiJiangnan
 * @date 2020/9/11
 */
@Getter
@AllArgsConstructor
public enum ApiErrorCode implements MessageCode {

    GLOBAL_EXPIRED_TOKEN_ERROR(100001, "global.expired.token.error"),
    GLOBAL_CLIENT_ERROR(100002, "global.client.error"),
    GLOBAL_BUSINESS_ERROR(100003, "global.business.error"),
    GLOBAL_SERVICE_BUSY_ERROR(100004, "global.service.busy.error"),
    GLOBAL_SERVICE_ERROR(100005, "global.service.error"),
    GLOBAL_CAPTCHA_ERROR(100006, "global.captcha.error"),
    ;

    private final int code;
    private final String messageCode;
}
