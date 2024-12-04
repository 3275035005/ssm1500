package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.FProjectCollect;
import com.intangible.mapper.FProjectCollectMapper;
import com.intangible.service.FProjectCollectService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FProjectCollectServiceImpl implements FProjectCollectService{

    @Autowired
    private FProjectCollectMapper forumMapper;


    @Override
    public PageResult<FProjectCollect> pageQuery(FProjectCollect data,int current, int size) {
        PageHelper.startPage(current, size);
        List<FProjectCollect> queryList = forumMapper.pageQuery(data);
        PageInfo<FProjectCollect> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(FProjectCollect data) {
        data.setCreateTime(Util.getTime());

        return forumMapper.insert(data);
    }

    @Override
    public int update(FProjectCollect data) {
        return forumMapper.update(data);
    }

    @Override
    public List<FProjectCollect> getAll() {
        return forumMapper.getAll();
    }

    @Override
    public void delete(int id) {
        forumMapper.delete(id);
    }

    @Override
    public FProjectCollect getById(Integer id) {
        return null;
    }

    @Override
    public FProjectCollect getOneByProjectIdAndUserId(FProjectCollect fProjectCollect) {
        return forumMapper.getOneByProjectIdAndUserId(fProjectCollect);
    }

    @Override
    public List<FProjectCollect> getProjectByUserId(Integer id) {
        return forumMapper.getProjectByUserId(id);
    }
}
