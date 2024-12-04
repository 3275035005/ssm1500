package com.intangible.service;

import com.intangible.entity.Order;

import java.util.List;

public interface OrderService extends BaseService<Order>{
    Order getOrderById(String id);

    List<Order> getOrderByUserId(Integer id);
}
