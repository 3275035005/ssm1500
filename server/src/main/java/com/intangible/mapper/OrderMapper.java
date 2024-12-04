package com.intangible.mapper;

import com.intangible.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderMapper继承基类
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {


    Order getOrderById(String id);

    List<Order> getOrderByUserId(Integer id);
}
