package com.intangible.mapper;

import com.intangible.entity.FProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FProjectMapper继承基类
 */
@Mapper
public interface FProjectMapper extends BaseMapper<FProject> {
    List<FProject> getByRecommendState(String recommendState);

}
