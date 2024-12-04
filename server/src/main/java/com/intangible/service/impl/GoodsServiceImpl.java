package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.Goods;
import com.intangible.mapper.GoodsMapper;
import com.intangible.service.GoodsService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsMapper GoodsMapper;


    @Override
    public PageResult<Goods> pageQuery(Goods data,int current, int size) {
        PageHelper.startPage(current, size);
        List<Goods> queryList = GoodsMapper.pageQuery(data);
        PageInfo<Goods> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public int save(Goods data) {
        data.setCreateTime(Util.getTime());
        return GoodsMapper.insert(data);
    }

    @Override
    public int update(Goods data) {
        return GoodsMapper.update(data);
    }

    @Override
    public List<Goods> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {
        GoodsMapper.delete(id);
    }

    @Override
    public Goods getById(Integer id) {
        return GoodsMapper.getById(id);
    }

}
