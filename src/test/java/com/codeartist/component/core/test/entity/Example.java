package com.codeartist.component.core.test.entity;

import lombok.Data;

/**
 * 测试类
 *
 * @author AiJiangnan
 * @date 2022/8/18
 */
@Data
public class Example {

    private String name;
    private Integer age;
    private Example example;
}
