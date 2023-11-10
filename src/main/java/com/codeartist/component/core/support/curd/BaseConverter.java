package com.codeartist.component.core.support.curd;

import java.util.List;

/**
 * 基础转换接口
 *
 * @author J.N.AI
 * @date 2023/6/1
 */
public interface BaseConverter<D, P, R> {

    D toDo(P param);

    R toVo(D role);

    List<R> toVo(List<D> roles);
}
