package com.codeartist.component.core.code;

import org.springframework.context.MessageSourceResolvable;

/**
 * 自定义错误码接口
 *
 * @author AiJiangnan
 * @date 2024/6/17
 */
public interface ErrorCode extends MessageSourceResolvable {

    String name();

    @Override
    default String[] getCodes() {
        return new String[]{name()};
    }
}
