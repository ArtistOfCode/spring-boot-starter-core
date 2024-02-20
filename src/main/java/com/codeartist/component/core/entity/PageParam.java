package com.codeartist.component.core.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "页码")
    private Integer pageNo = 1;
    /**
     * 每页记录数
     */
    @Schema(description = "每页记录数")
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
