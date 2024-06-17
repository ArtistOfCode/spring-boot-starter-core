package com.codeartist.component.core.web;

import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.code.ApiErrorCode;
import com.codeartist.component.core.entity.ResponseError;
import com.codeartist.component.core.entity.enums.ApiHttpStatus;
import com.codeartist.component.core.entity.enums.Environments;
import com.codeartist.component.core.exception.BusinessException;
import com.codeartist.component.core.exception.FeignException;
import com.codeartist.component.core.support.metric.Metrics;
import com.codeartist.component.core.support.props.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务端异常处理
 *
 * @author AiJiangnan
 * @date 2020/9/11
 */
@Slf4j
@Order(2)
@ControllerAdvice
public class ServerExceptionHandler {

    @Autowired
    private AppProperties appProperties;
    @Autowired
    private Metrics metrics;

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<ResponseError> assertException(RuntimeException e) {
        ResponseError error = new ResponseError(appProperties.getName(), ApiErrorCode.GLOBAL_BUSINESS_ERROR, e.getMessage());
        return ResponseEntity
                .status(ApiHttpStatus.BUSINESS_WARNING.getValue())
                .body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseError> businessException(BusinessException e) {
        List<ObjectError> allErrors = e.getAllErrors();

        if (CollectionUtils.isEmpty(allErrors)) {
            log.error("errors is empty.", e);
            return ResponseEntity.status(ApiHttpStatus.BUSINESS_WARNING.getValue()).build();
        }

        ObjectError first = allErrors.stream().findFirst().get();
        ResponseError error = new ResponseError(appProperties.getName(), first.getCode(), SpringContext.getMessage(first));

        List<ResponseError.BusinessError> errors = allErrors.stream()
                .map(g -> new ResponseError.BusinessError(g.getCode(), SpringContext.getMessage(g)))
                .collect(Collectors.toList());

        error.setErrors(errors);

        return ResponseEntity
                .status(ApiHttpStatus.BUSINESS_WARNING.getValue())
                .body(error);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ResponseError> feignException(FeignException e) {
        ResponseError error = e.getResponseError();
        log.error("Feign exception at {}, {}:{}, {}", e.getMethodKey(), error.getCode(), error.getMessage(), error.getStackTrace(), e);
        return ResponseEntity
                .status(ApiHttpStatus.SERVER_ERROR.getValue())
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> exception(Exception e, HttpServletRequest request) {
        log.error("Server exception", e);
        String method = request.getMethod();

        String uri = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (uri != null) {
            metrics.counter("api_error", "method", method, "uri", uri);
        }

        ApiErrorCode errorCode = ApiErrorCode.GLOBAL_SERVICE_ERROR;
        String message = SpringContext.getMessage(errorCode);
        ResponseError error = new ResponseError(appProperties.getName(), errorCode, message, trace(e, method, uri));

        return ResponseEntity
                .status(ApiHttpStatus.SERVER_ERROR.getValue())
                .body(error);
    }

    private String trace(Exception e, String method, String uri) {
        if (Environments.PROD.is()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("API: ").append(method).append(" ").append(uri).append("\n");
        printStackTrace(sb, e);
        return sb.toString();
    }

    private void printStackTrace(StringBuilder message, Throwable e) {
        message.append(e).append("\n");
        for (StackTraceElement traceElement : e.getStackTrace()) {
            message.append("\tat ").append(traceElement).append("\n");
        }
        for (Throwable se : e.getSuppressed()) {
            printStackTrace(message, se);
        }
        if (e.getCause() != null) {
            printStackTrace(message, e.getCause());
        }
    }
}
