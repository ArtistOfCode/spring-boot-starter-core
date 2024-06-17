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
public enum ApiErrorCode implements ErrorCode {

    GLOBAL_EXPIRED_TOKEN_ERROR,
    GLOBAL_CLIENT_ERROR,
    GLOBAL_BUSINESS_ERROR,
    GLOBAL_SERVICE_BUSY_ERROR,
    GLOBAL_SERVICE_ERROR,
    GLOBAL_CAPTCHA_EXPIRE,
    GLOBAL_CAPTCHA_ERROR,
}
