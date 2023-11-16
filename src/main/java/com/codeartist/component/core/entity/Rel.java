package com.codeartist.component.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 关联查询
 *
 * @author AiJiangnan
 * @date 2023-10-07
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Rel<T> {

    private final Class<T> clazz;
    private final String one;
    private final String more;

    public static <T> Rel<T> of(Class<T> clazz, String one, String more) {
        return new Rel<>(clazz, one, more);
    }
}
