package com.intangible.mapper;

import com.intangible.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AddressMapper继承基类
 */
@Repository
public interface AddressMapper extends BaseMapper<Address> {

    List<Address> selectByUserId(Integer userId);
}
