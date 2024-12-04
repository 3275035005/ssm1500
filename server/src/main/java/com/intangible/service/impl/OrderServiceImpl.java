package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.Order;
import com.intangible.mapper.OrderMapper;
import com.intangible.service.OrderService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper OrderMapper;


    @Override
    public PageResult<Order> pageQuery(Order data,int current, int size) {
        PageHelper.startPage(current, size);
        List<Order> queryList = OrderMapper.pageQuery(data);
        PageInfo<Order> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public int save(Order data) {
        data.setCreateTime(Util.getTime());
         OrderMapper.insert(data);

        return 1;
    }

    @Override
    public int update(Order data) {
        return OrderMapper.update(data);
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {
        OrderMapper.delete(id);
    }

    @Override
    public Order getById(Integer id) {
        return null;
    }

    @Override
    public Order getOrderById(String id) {
        return OrderMapper.getOrderById(id);
    }

    @Override
    public List<Order> getOrderByUserId(Integer id) {
        return OrderMapper.getOrderByUserId(id);
    }
}
