package com.intangible.mapper;

import com.intangible.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * UsersMapper继承基类
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    Users getUsersByUsername(String username);

    Users getById(Integer id);
}