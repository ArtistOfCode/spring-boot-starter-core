package com.codeartist.component.core.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * HTTP 接口响应异常实体
 *
 * @author AiJiangnan
 * @date 2022/7/22
 */
@Getter
@Setter
public class ResponseError {

    private String service;
    private int code;
    private String message;
    private String stackTrace;

    public ResponseError() {
    }

    public ResponseError(String service, int code, String message) {
        this(service, code, message, null);
    }

    public ResponseError(String service, int code, String message, String stackTrace) {
        this.service = service;
        this.code = code;
        this.message = message;
        this.stackTrace = stackTrace;
    }
}
