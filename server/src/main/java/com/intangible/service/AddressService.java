package com.intangible.service;

import com.intangible.entity.Address;
import com.intangible.entity.Forum;

import java.util.List;

public interface AddressService extends BaseService<Address>{

    List<Address> selectByUserId(Integer userId);

}
