package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.Forum;
import com.intangible.mapper.ForumMapper;
import com.intangible.service.ForumService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumServiceImpl implements ForumService{

    @Autowired
    private ForumMapper forumMapper;


    @Override
    public PageResult<Forum> pageQuery(Forum data,int current, int size) {
        PageHelper.startPage(current, size);
        List<Forum> queryList = forumMapper.pageQuery(data);
        PageInfo<Forum> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Forum data) {
        data.setCreateTime(Util.getTime());

        return forumMapper.insert(data);
    }

    @Override
    public int update(Forum data) {
        return forumMapper.update(data);
    }

    @Override
    public List<Forum> getAll() {
        return forumMapper.getAll();
    }

    @Override
    public void delete(int id) {
        forumMapper.delete(id);
    }

    @Override
    public Forum getById(Integer id) {
        return null;
    }
}
