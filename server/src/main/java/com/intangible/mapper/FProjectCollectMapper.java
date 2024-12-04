package com.intangible.mapper;

import com.intangible.entity.FProjectCollect;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FProjectCollectMapper继承基类
 */
@Repository
public interface FProjectCollectMapper extends BaseMapper<FProjectCollect> {

    FProjectCollect getOneByProjectIdAndUserId(FProjectCollect fProjectCollect);

    List<FProjectCollect> getProjectByUserId(Integer id);
}
