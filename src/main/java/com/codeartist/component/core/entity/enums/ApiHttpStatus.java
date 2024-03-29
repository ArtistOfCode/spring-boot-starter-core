package com.codeartist.component.core.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义业务异常HTTP状态码
 *
 * @author AiJiangnan
 * @date 2023/7/25
 */
@Getter
@AllArgsConstructor
public enum ApiHttpStatus {
    CLIENT_WARNING(498, "Client Warning"),
    BUSINESS_WARNING(499, "Business Warning"),
    SERVER_ERROR(599, "Server Error"),
    ;

    private final int value;
    private final String reasonPhrase;

    public static boolean isWarning(int status) {
        return status == CLIENT_WARNING.getValue() || status == BUSINESS_WARNING.getValue();
    }

    public static boolean isError(int status) {
        return status == SERVER_ERROR.getValue();
    }
}
