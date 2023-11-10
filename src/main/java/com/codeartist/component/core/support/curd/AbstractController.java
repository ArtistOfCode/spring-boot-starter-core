package com.codeartist.component.core.support.curd;

import com.codeartist.component.core.entity.PageInfo;
import com.codeartist.component.core.entity.PageParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 抽象控制类
 *
 * @author J.N.AI
 * @date 2023/6/1
 */
@Getter
public abstract class AbstractController<R, P extends PageParam> {

    @Autowired
    private BaseService<R, P> service;

    @GetMapping
    @Operation(summary = "查询详情接口")
    public R get(Long id) {
        return getService().get(id);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询接口")
    public PageInfo<R> get(P param) {
        return getService().get(param);
    }

    @PostMapping
    @Operation(summary = "新建更新接口")
    public void save(@RequestBody P param) {
        getService().save(param);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除接口")
    public void delete(@PathVariable Long id) {
        getService().delete(id);
    }
}
