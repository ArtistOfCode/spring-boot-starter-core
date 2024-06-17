package com.codeartist.component.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

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
    private String code;
    private String message;
    private String stackTrace;
    private List<BusinessError> errors;

    public ResponseError() {
    }

    public ResponseError(String service, Enum<?> code, String message) {
        this(service, code, message, null);
    }

    public ResponseError(String service, Enum<?> code, String message, String stackTrace) {
        this(service, code, message, stackTrace, Collections.emptyList());
    }

    public ResponseError(String service, Enum<?> code, String message, String stackTrace, List<BusinessError> errors) {
        this(service, code.name(), message, stackTrace, errors);
    }

    public ResponseError(String service, String code, String message) {
        this(service, code, message, null);
    }

    public ResponseError(String service, String code, String message, String stackTrace) {
        this(service, code, message, stackTrace, Collections.emptyList());
    }

    public ResponseError(String service, String code, String message, String stackTrace, List<BusinessError> errors) {
        this.service = service;
        this.code = code;
        this.message = message;
        this.stackTrace = stackTrace;
        this.errors = errors;
    }

    @Getter
    @Setter
    public static class BusinessError {
        private String code;
        private String message;

        public BusinessError(Enum<?> code, String message) {
            this(code.name(), message);
        }

        public BusinessError(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
