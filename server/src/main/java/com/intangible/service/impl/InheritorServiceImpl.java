package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.FInheritor;
import com.intangible.mapper.FInheritorMapper;
import com.intangible.service.InheritorService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InheritorServiceImpl implements InheritorService {

    @Autowired
    private FInheritorMapper FInheritorMapper;


    @Override
    public PageResult<FInheritor> pageQuery(FInheritor data,int current, int size) {
        PageHelper.startPage(current, size);
        List<FInheritor> queryList = FInheritorMapper.pageQuery(data);
        PageInfo<FInheritor> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(FInheritor data) {
        data.setCreateTime(Util.getTime());
        return FInheritorMapper.insert(data);
    }

    @Override
    public int update(FInheritor data) {
        return FInheritorMapper.update(data);
    }

    @Override
    public List<FInheritor> getAll() {
        return FInheritorMapper.getAll();
    }

    @Override
    public void delete(int id) {
        FInheritorMapper.delete(id);
    }

    @Override
    public FInheritor getById(Integer id) {
        return FInheritorMapper.getById(id);
    }
}
