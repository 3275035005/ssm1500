package com.intangible.controller;

import com.intangible.entity.Goods;
import com.intangible.service.GoodsService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对管理平台提供 非遗物品管理接口
 */
@RequestMapping("goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页添加查询数据
     * @param current 当前页码
     * @param size 每页大小
     * @param forum 查询数据
     * @return
     */
    @PostMapping("pageQuery/{current}/{size}")
    public R pageQuery(@PathVariable int current,
                       @PathVariable int size,
                       @RequestBody Goods forum){
        PageResult<Goods> pageResult = goodsService.pageQuery(forum, current, size);
        return R.ok().put("list", pageResult);
    }


    /**
     * 新增数据
     * @param forum
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody Goods forum){
        goodsService.save(forum);
        return R.ok();
    }

    /**
     * 修改数据
     * @param forum
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Goods forum){
        goodsService.update(forum);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        goodsService.delete(id);
        return R.ok();
    }
}
