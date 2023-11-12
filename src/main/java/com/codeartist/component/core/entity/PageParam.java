package com.codeartist.component.core.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页参数
 *
 * @author AiJiangnan
 * @date 2020/9/21
 */
@Getter
@Setter
public abstract class PageParam implements IdParam, UpdateParam {

    /**
     * 页码
     */
    @JsonIgnore
    private Integer pageNo = 1;
    /**
     * 每页记录数
     */
    @JsonIgnore
    private Integer pageSize = 10;

    public <T> IPage<T> page() {
        pageNo = pageNo < 1 ? 1 : pageNo;
        pageSize = pageSize < 0 ? 10 : (pageSize > 200 ? 200 : pageSize);
        return new Page<>(pageNo, pageSize);
    }

    public <T> IPage<T> longPage() {
        pageNo = pageNo < 1 ? 1 : pageNo;
        pageSize = pageSize < 0 ? 10 : (pageSize > 1000 ? 1000 : pageSize);
        return new Page<>(pageNo, pageSize);
    }
}
