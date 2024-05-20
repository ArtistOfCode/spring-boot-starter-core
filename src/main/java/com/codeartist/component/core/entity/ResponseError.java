package com.codeartist.component.core.entity;

import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.code.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<BusinessError> errors;

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

    public void setErrors(List<MessageCode> messageCodeList) {
        this.errors = messageCodeList.stream()
                .map(m -> new BusinessError(m.getCode(), SpringContext.getMessage(m)))
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BusinessError {
        private int code;
        private String message;
    }
}
