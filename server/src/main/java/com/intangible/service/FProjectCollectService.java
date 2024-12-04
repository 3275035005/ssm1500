package com.intangible.service;

import com.intangible.entity.FProjectCollect;

import java.util.List;

public interface FProjectCollectService extends BaseService<FProjectCollect>{


    FProjectCollect  getOneByProjectIdAndUserId(FProjectCollect fProjectCollect);

    List<FProjectCollect> getProjectByUserId(Integer id);
}
