package com.intangible.service;

import com.intangible.entity.Theme;

import java.util.List;

public interface ThemeService extends BaseService<Theme>{

    /**
     * 根据是否为推荐主题 查询
     * @param recommendState
     * @return
     */
    List<Theme> getByRecommendState(String recommendState);
}
