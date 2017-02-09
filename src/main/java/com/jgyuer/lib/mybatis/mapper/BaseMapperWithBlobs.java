package com.jgyuer.lib.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapperWithBlobs<R, RE> extends BaseMapper<R, RE> {

    List<R> selectByExampleWithBLOBs(RE example);

    int updateByExampleWithBLOBs(@Param("record") R record, @Param("example") RE example);
}
