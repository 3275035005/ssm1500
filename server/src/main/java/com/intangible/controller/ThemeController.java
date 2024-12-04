package com.intangible.controller;

import com.intangible.entity.Theme;
import com.intangible.service.ThemeService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对管理平台提供 主题信息管理接口
 */
@RequestMapping("theme")
@RestController
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    /**
     * 分页添加查询数据
     * @param current 当前页码
     * @param size 每页大小
     * @param theme 查询数据
     * @return
     */
    @PostMapping("pageQuery/{current}/{size}")
    public R pageQuery(@PathVariable int current,
                       @PathVariable int size,
                       @RequestBody Theme theme){
        PageResult<Theme> pageResult = themeService.pageQuery(theme, current, size);
        return R.ok().put("list", pageResult);
    }


    /**
     * 新增数据
     * @param theme
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody Theme theme){
        themeService.save(theme);
        return R.ok();
    }

    /**
     * 修改数据
     * @param theme
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Theme theme){
        themeService.update(theme);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        themeService.delete(id);
        return R.ok();
    }
}
