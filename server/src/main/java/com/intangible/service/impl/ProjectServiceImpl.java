package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.FProject;
import com.intangible.mapper.FProjectMapper;
import com.intangible.service.ProjectService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private FProjectMapper FProjectMapper;


    @Override
    public PageResult<FProject> pageQuery(FProject data,int current, int size) {
        PageHelper.startPage(current, size);
        List<FProject> queryList = FProjectMapper.pageQuery(data);
        PageInfo<FProject> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(FProject data) {
        data.setCreateTime(Util.getTime());
        return FProjectMapper.insert(data);
    }

    @Override
    public int update(FProject data) {
        return FProjectMapper.update(data);
    }

    @Override
    public List<FProject> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {
        FProjectMapper.delete(id);
    }

    @Override
    public FProject getById(Integer id) {
        return FProjectMapper.getById(id);
    }


    @Override
    public List<FProject> getByRecommendState(String recommendState) {
        return FProjectMapper.getByRecommendState(recommendState);
    }

}
