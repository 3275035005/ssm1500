package com.intangible.service;

import com.intangible.entity.Users;

public interface UsersService extends BaseService<Users>{

    /**
     * 通过账号查询数据
     * @param username
     * @return
     */
    Users getUsersByUsername(String username);

}
