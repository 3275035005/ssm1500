package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.Theme;
import com.intangible.mapper.ThemeMapper;
import com.intangible.service.ThemeService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService{

    @Autowired
    private ThemeMapper ThemeMapper;


    @Override
    public PageResult<Theme> pageQuery(Theme data,int current, int size) {
        PageHelper.startPage(current, size);
        List<Theme> queryList = ThemeMapper.pageQuery(data);
        PageInfo<Theme> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Theme data) {
        data.setCreateTime(Util.getTime());
        return ThemeMapper.insert(data);
    }

    @Override
    public int update(Theme data) {
        return ThemeMapper.update(data);
    }

    @Override
    public List<Theme> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {
        ThemeMapper.delete(id);
    }

    @Override
    public Theme getById(Integer id) {
        return ThemeMapper.getById(id);
    }

    @Override
    public List<Theme> getByRecommendState(String recommendState) {
        return ThemeMapper.getByRecommendState(recommendState);
    }
}
