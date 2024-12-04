package com.intangible.controller;

import com.intangible.entity.FInheritor;
import com.intangible.service.InheritorService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对管理平台提供 非遗传承人管理接口
 */
@RequestMapping("inheritor")
@RestController
public class InheritorController {

    @Autowired
    private InheritorService inheritorService;

    /**
     * 分页添加查询数据
     * @param current 当前页码
     * @param size 每页大小
     * @param inheritor 查询数据
     * @return
     */
    @PostMapping("pageQuery/{current}/{size}")
    public R pageQuery(@PathVariable int current,
                       @PathVariable int size,
                       @RequestBody FInheritor inheritor){
        PageResult<FInheritor> pageResult = inheritorService.pageQuery(inheritor, current, size);
        return R.ok().put("list", pageResult);
    }


    /**
     * 新增数据
     * @param inheritor
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody FInheritor inheritor){
        inheritorService.save(inheritor);
        return R.ok();
    }

    /**
     * 修改数据
     * @param inheritor
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody FInheritor inheritor){
        inheritorService.update(inheritor);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        inheritorService.delete(id);
        return R.ok();
    }

    /**
     * 删除数据
     * @return
     */
    @GetMapping("getAll")
    public R getAll(){
        return R.ok().put("data",  inheritorService.getAll());
    }
}
