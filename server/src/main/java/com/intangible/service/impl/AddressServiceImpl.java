package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.Address;
import com.intangible.mapper.AddressMapper;
import com.intangible.service.AddressService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressMapper addressMapper;


    @Override
    public PageResult<Address> pageQuery(Address data,int current, int size) {

        return null;
    }

    @Override
    public int save(Address data) {
        return addressMapper.insert(data);
    }

    @Override
    public int update(Address data) {
        return addressMapper.update(data);
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {
        addressMapper.delete(id);
    }

    @Override
    public Address getById(Integer id) {
        return null;
    }

    @Override
    public List<Address> selectByUserId(Integer userId) {
        return addressMapper.selectByUserId(userId);
    }
}
