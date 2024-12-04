package com.intangible.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intangible.entity.Notice;
import com.intangible.mapper.NoticeMapper;
import com.intangible.service.NoticeService;
import com.intangible.utils.PageResult;
import com.intangible.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeMapper NoticeMapper;


    @Override
    public PageResult<Notice> pageQuery(Notice data,int current, int size) {
        PageHelper.startPage(current, size);
        List<Notice> queryList = NoticeMapper.pageQuery(data);
        PageInfo<Notice> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public int save(Notice data) {
        data.setCreateTime(Util.getTime());
        return NoticeMapper.insert(data);
    }

    @Override
    public int update(Notice data) {
        return NoticeMapper.update(data);
    }

    @Override
    public List<Notice> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {
        NoticeMapper.delete(id);
    }

    @Override
    public Notice getById(Integer id) {
        return null;
    }

    @Override
    public List<String> getByType(String type) {
        return NoticeMapper.getByType(type);
    }
}
