package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.FProjectClassify;
import com.intangible.mapper.FProjectClassifyMapper;
import com.intangible.service.FProjectClassifyService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FProjectClassifyServiceImpl implements FProjectClassifyService{

    @Autowired
    private FProjectClassifyMapper forumMapper;


    @Override
    public PageResult<FProjectClassify> pageQuery(FProjectClassify data,int current, int size) {
        PageHelper.startPage(current, size);
        List<FProjectClassify> queryList = forumMapper.pageQuery(data);
        PageInfo<FProjectClassify> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(FProjectClassify data) {
        data.setCreateTime(Util.getTime());

        return forumMapper.insert(data);
    }

    @Override
    public int update(FProjectClassify data) {
        return forumMapper.update(data);
    }

    @Override
    public List<FProjectClassify> getAll() {
        return forumMapper.getAll();
    }

    @Override
    public void delete(int id) {
        forumMapper.delete(id);
    }

    @Override
    public FProjectClassify getById(Integer id) {
        return null;
    }
}
