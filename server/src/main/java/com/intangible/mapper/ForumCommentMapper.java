package com.intangible.mapper;

import com.intangible.entity.ForumComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ForumCommentMapper extends BaseMapper<ForumComment> {

    List<ForumComment> getListByForumId(@Param("forumId") Integer forumId);
}