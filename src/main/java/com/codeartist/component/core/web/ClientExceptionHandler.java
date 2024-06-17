package com.codeartist.component.core.web;

import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.code.ApiErrorCode;
import com.codeartist.component.core.entity.ResponseError;
import com.codeartist.component.core.entity.enums.ApiHttpStatus;
import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.support.props.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 客户端异常处理
 *
 * @author AiJiangnan
 * @date 2022/4/22
 */
@Slf4j
@Order(1)
@ControllerAdvice
public class ClientExceptionHandler {

    @Autowired
    private AppProperties appProperties;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> constraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        if (CollectionUtils.isEmpty(violations)) {
            log.error("ConstraintViolation is empty.", e);
            return ResponseEntity.status(ApiHttpStatus.CLIENT_WARNING.getValue()).build();
        }

        ConstraintViolation<?> first = violations.stream().findFirst().get();
        ResponseError error = new ResponseError(appProperties.getName(), ApiErrorCode.GLOBAL_CLIENT_ERROR, first.getMessage());

        List<ResponseError.BusinessError> errors = violations.stream()
                .map(vio -> new ResponseError.BusinessError(ApiErrorCode.GLOBAL_CLIENT_ERROR, vio.getMessage()))
                .collect(Collectors.toList());

        error.setErrors(errors);

        return ResponseEntity
                .status(ApiHttpStatus.CLIENT_WARNING.getValue())
                .body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseError> bindException(BindException e) {
        return badRequestException(new BadRequestException(e));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseError> badRequestException(BadRequestException e) {
        List<ObjectError> allErrors = e.getAllErrors();

        if (CollectionUtils.isEmpty(allErrors)) {
            log.error("errors is empty.", e);
            return ResponseEntity.status(ApiHttpStatus.CLIENT_WARNING.getValue()).build();
        }

        ObjectError first = allErrors.stream().findFirst().get();
        ResponseError error = new ResponseError(appProperties.getName(), first.getCode(), SpringContext.getMessage(first));

        List<ResponseError.BusinessError> errors = allErrors.stream()
                .map(f -> new ResponseError.BusinessError(f.getCode(), SpringContext.getMessage(f)))
                .collect(Collectors.toList());

        error.setErrors(errors);

        return ResponseEntity
                .status(ApiHttpStatus.CLIENT_WARNING.getValue())
                .body(error);
    }

}
