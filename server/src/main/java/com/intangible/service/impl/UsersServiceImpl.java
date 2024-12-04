package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.Users;
import com.intangible.mapper.UsersMapper;
import com.intangible.service.UsersService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersMapper usersMapper;


    @Override
    public PageResult<Users> pageQuery(Users data,int current, int size) {
        PageHelper.startPage(current, size);
        List<Users> queryList = usersMapper.pageQuery(data);
        PageInfo<Users> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Users data) {
        data.setCreateTime(Util.getTime());
        return usersMapper.insert(data);
    }

    @Override
    public int update(Users data) {
        return usersMapper.update(data);
    }

    @Override
    public List<Users> getAll() {
        return usersMapper.getAll();
    }

    @Override
    public void delete(int id) {
        usersMapper.delete(id);
    }


    @Override
    public Users getUsersByUsername(String username) {
        return usersMapper.getUsersByUsername(username);
    }

    @Override
    public Users getById(Integer id) {
        return usersMapper.getById(id);
    }
}
