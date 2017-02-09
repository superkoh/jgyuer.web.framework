package com.jgyuer.lib.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<R, RE> extends IMapper {

    int countByExample(RE example);

    int deleteByExample(RE example);

    int insert(R record);

    int insertSelective(R record);

    List<R> selectByExample(RE example);

    int updateByExampleSelective(@Param("record") R record, @Param("example") RE example);

    int updateByExample(@Param("record") R record, @Param("example") RE example);
}
