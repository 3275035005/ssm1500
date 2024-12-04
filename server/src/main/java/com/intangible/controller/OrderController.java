package com.intangible.controller;

import com.intangible.entity.Order;
import com.intangible.service.OrderService;
import com.intangible.service.OrderService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对管理平台提供 非遗物品管理接口
 */
@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

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
                       @RequestBody Order forum){
        PageResult<Order> pageResult = orderService.pageQuery(forum, current, size);
        return R.ok().put("list", pageResult);
    }



    /**
     * 发货订单
     * @param forum
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Order forum){
        orderService.update(forum);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        orderService.delete(id);
        return R.ok();
    }
}
