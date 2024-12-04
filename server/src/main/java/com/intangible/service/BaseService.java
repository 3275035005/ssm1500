package com.intangible.service;

import com.intangible.entity.FProject;
import com.intangible.utils.PageResult;

import java.util.List;

public interface BaseService<T> {

    /**
     * 分页添加查询
     * @param data
     * @return
     */
    PageResult<T> pageQuery(T data, int current, int size);

    /**
     * 新增数据
     * @param data
     * @return
     */
    int save(T data);

    /**
     * 修改
     * @param data
     * @return
     */
    int update(T data);

    /**
     * 查询所有
     * @param
     * @return
     */
    List<T> getAll();

    /**
     * 删除
     * @param
     * @return
     */
    void delete(int id);

    /**
     * 通过id查询数据
     * @param id
     * @return
     */
    T getById(Integer id);
}
