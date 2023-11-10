package com.codeartist.component.core.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务异常HTTP状态码
 *
 * @author J.N.AI
 * @date 2023/7/25
 */
@Getter
@AllArgsConstructor
public enum ApiHttpStatus {
    CLIENT_WARNING(499, "Client warning."),
    BUSINESS_WARNING(599, "Business warning."),
    ;

    private final int value;
    private final String reasonPhrase;

    public static boolean in(int status) {
        return status == CLIENT_WARNING.getValue() || status == BUSINESS_WARNING.getValue();
    }
}
