package com.codeartist.component.core.code;

import org.springframework.context.MessageSourceResolvable;

/**
 * 错误码接口
 *
 * @author AiJiangnan
 * @date 2020/9/11
 */
public interface MessageCode extends MessageSourceResolvable {

    int getCode();

    String getMessageCode();

    @Override
    default String[] getCodes() {
        return new String[]{getMessageCode()};
    }
}
