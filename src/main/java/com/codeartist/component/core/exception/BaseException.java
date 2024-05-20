package com.codeartist.component.core.exception;

import com.codeartist.component.core.SpringContext;
import com.codeartist.component.core.code.DefaultMessageCode;
import com.codeartist.component.core.code.MessageCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础异常
 *
 * @author AiJiangnan
 * @date 2024/5/20
 */
@Getter
@Setter
public abstract class BaseException extends RuntimeException {

    private final MessageCode messageCode;
    private List<MessageCode> businessMessage;

    public BaseException(MessageCode messageCode) {
        super(SpringContext.getMessage(messageCode));
        this.messageCode = messageCode;
    }

    public BaseException(int code, String defaultMessage) {
        this(new DefaultMessageCode(code, defaultMessage));
    }

    public BaseException(int code, String defaultMessage, Object... args) {
        this(new DefaultMessageCode(code, defaultMessage, args));
    }

    public void addBusinessMessage(MessageCode messageCode) {
        if (businessMessage == null) {
            businessMessage = new ArrayList<>();
        }
        businessMessage.add(messageCode);
    }
}
