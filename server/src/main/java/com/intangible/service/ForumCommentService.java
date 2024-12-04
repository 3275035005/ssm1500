package com.intangible.service;

import com.intangible.entity.Forum;
import com.intangible.entity.ForumComment;

import java.util.List;

public interface ForumCommentService extends BaseService<ForumComment>{

    /**
     * 根据论坛id查询评论数据
     * @param forumId
     * @return
     */
    List<ForumComment> getListByForumId(Integer forumId);
}
