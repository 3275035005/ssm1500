package com.intangible.mapper;

import com.intangible.entity.Theme;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ThemeMapper继承基类
 */
@Repository
public interface ThemeMapper extends BaseMapper<Theme> {
    List<Theme> getByRecommendState(@Param("recommendState") String recommendState);

}