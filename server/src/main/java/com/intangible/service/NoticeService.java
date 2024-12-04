package com.intangible.service;

import com.intangible.entity.Notice;

import java.util.List;

public interface NoticeService extends BaseService<Notice>{

    /**
     * 按照公告类型查询数据
     * @param type
     * @return
     */
    List<String> getByType(String type);
}
