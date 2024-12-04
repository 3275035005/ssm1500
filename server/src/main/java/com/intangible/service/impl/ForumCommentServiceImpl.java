package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.ForumComment;
import com.intangible.mapper.ForumCommentMapper;
import com.intangible.service.ForumCommentService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumCommentServiceImpl implements ForumCommentService {

    @Autowired
    private ForumCommentMapper forumCommentMapper;


    @Override
    public PageResult<ForumComment> pageQuery(ForumComment data, int current, int size) {
        PageHelper.startPage(current, size);
        List<ForumComment> queryList = forumCommentMapper.pageQuery(data);
        PageInfo<ForumComment> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(ForumComment data) {
        data.setCreateTime(Util.getTime());

        return forumCommentMapper.insert(data);
    }

    @Override
    public int update(ForumComment data) {
        return forumCommentMapper.update(data);
    }

    @Override
    public List<ForumComment> getAll() {
        return forumCommentMapper.getAll();
    }

    @Override
    public void delete(int id) {
        forumCommentMapper.delete(id);
    }

    @Override
    public ForumComment getById(Integer id) {
        return null;
    }

    @Override
    public List<ForumComment> getListByForumId(Integer forumId) {
        return forumCommentMapper.getListByForumId(forumId);
    }
}
