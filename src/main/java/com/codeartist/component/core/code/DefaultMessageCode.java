package com.codeartist.component.core.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误码默认实现
 *
 * @author AiJiangnan
 * @date 2023-12-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultMessageCode implements MessageCode {

    private int code;
    private String messageCode;
    private String defaultMessage;
    private Object[] arguments;

    public DefaultMessageCode(int code, String defaultMessage) {
        this(code, defaultMessage, null);
    }

    public DefaultMessageCode(int code, String defaultMessage, Object[] arguments) {
        this(code, null, defaultMessage, arguments);
    }
}
