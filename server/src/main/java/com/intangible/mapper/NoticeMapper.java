package com.intangible.mapper;

import com.intangible.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NoticeMapper继承基类
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {

    List<String> getByType(String type);
}