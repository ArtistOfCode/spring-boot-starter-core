package com.codeartist.component.core.code;

import lombok.Data;

/**
 * 错误码默认实现
 *
 * @author AiJiangnan
 * @date 2023-12-12
 */
@Data
public class DefaultMessageCode implements MessageCode {

    private final int code;
    private final String messageCode;
    private final String defaultMessage;
    private final Object[] arguments;
}
