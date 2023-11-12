package com.codeartist.component.core.test.entity;

import com.codeartist.component.core.entity.ICode;

/**
 * 测试码
 *
 * @author J.N.AI
 * @date 2023-11-12
 */
public class DemoCode implements ICode {

    @Override
    public int getCode() {
        return 100000;
    }

    @Override
    public String getName() {
        return "Test code";
    }
}
