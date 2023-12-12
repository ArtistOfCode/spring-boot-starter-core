package com.codeartist.component.core.test.entity;

import com.codeartist.component.core.code.MessageCode;

/**
 * 测试码
 *
 * @author AiJiangnan
 * @date 2023-11-12
 */
public class DemoCode implements MessageCode {

    @Override
    public int getCode() {
        return 100000;
    }

    @Override
    public String getMessageCode() {
        return "";
    }
}
