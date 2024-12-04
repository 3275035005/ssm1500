package com.intangible.controller;

import com.intangible.entity.ForumComment;
import com.intangible.service.ForumCommentService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对管理平台提供 论坛评论数据管理接口
 */
@RequestMapping("forumComment")
@RestController
public class ForumCommentController {

    @Autowired
    private ForumCommentService forumService;

    /**
     * 根据论坛Id查询数据
     * @return
     */
    @GetMapping("getListByForumId")
    public R getListByForumId(Integer forumId){
        List<ForumComment> listByForumId = forumService.getListByForumId(forumId);
        return R.ok().put("list", listByForumId);
    }


    /**
     * 新增数据
     * @param forum
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody ForumComment forum){
        forumService.save(forum);
        return R.ok();
    }

    /**
     * 修改数据
     * @param forum
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody ForumComment forum){
        forumService.update(forum);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        forumService.delete(id);
        return R.ok();
    }
}
