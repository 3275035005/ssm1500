package com.intangible.service;

import com.intangible.entity.FProject;

import java.util.List;

public interface ProjectService extends BaseService<FProject>{

    /**
     * 根据是否为推荐项目 查询
     * @param recommendState
     * @return
     */
    List<FProject> getByRecommendState(String recommendState);


}
