package com.intangible.controller;

import com.intangible.entity.Notice;
import com.intangible.service.NoticeService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对管理平台提供 公告管理接口
 */
@RequestMapping("notice")
@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

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
                       @RequestBody Notice notice){
        PageResult<Notice> pageResult = noticeService.pageQuery(notice, current, size);
        return R.ok().put("list", pageResult);
    }


    /**
     * 新增数据
     * @param notice
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody Notice notice){
        noticeService.save(notice);
        return R.ok();
    }

    /**
     * 修改数据
     * @param notice
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Notice notice){
        noticeService.update(notice);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        noticeService.delete(id);
        return R.ok();
    }
}
