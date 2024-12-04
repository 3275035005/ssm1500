package com.intangible.controller;

import com.intangible.entity.FProjectClassify;
import com.intangible.service.FProjectClassifyService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对管理平台提供 非遗项目分类管理接口
 */
@RequestMapping("classify")
@RestController
public class FProjectClassifyController {

    @Autowired
    private FProjectClassifyService fProjectClassifyService;

    /**
     * 分页添加查询数据
     * @param current 当前页码
     * @param size 每页大小
     * @param notice 查询数据
     * @return
     */
    @PostMapping("pageQuery/{current}/{size}")
    public R pageQuery(@PathVariable int current,
                       @PathVariable int size,
                       @RequestBody FProjectClassify notice){
        PageResult<FProjectClassify> pageResult = fProjectClassifyService.pageQuery(notice, current, size);
        return R.ok().put("list", pageResult);
    }


    /**
     * 新增数据
     * @param notice
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody FProjectClassify notice){
        fProjectClassifyService.save(notice);
        return R.ok();
    }

    /**
     * 修改数据
     * @param notice
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody FProjectClassify notice){
        fProjectClassifyService.update(notice);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        fProjectClassifyService.delete(id);
        return R.ok();
    }

    /**
     * 所有分类查询
     */
    @GetMapping("getClassifyAll")
    public R getClassifyAll(){
        List<FProjectClassify> all = fProjectClassifyService.getAll();
        return R.ok().put("list", all);
    }
}
