package com.codeartist.component.core.support.curd;

import com.codeartist.component.core.entity.PageInfo;

/**
 * 基础服务类
 *
 * @author 艾江南
 * @since 2022-08-31
 */
public interface BaseService<R, P> {

    R get(Long id);

    PageInfo<R> get(P param);

    void save(P param);

    void delete(Long id);
}
