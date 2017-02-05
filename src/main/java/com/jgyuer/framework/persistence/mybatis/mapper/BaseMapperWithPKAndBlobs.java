package com.jgyuer.framework.persistence.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapperWithPKAndBlobs<R, RE, K> extends BaseMapperWithPK<R, RE, K> {

    List<R> selectByExampleWithBLOBs(RE example);

    int updateByExampleWithBLOBs(@Param("record") R record, @Param("example") RE example);

    int updateByPrimaryKeyWithBLOBs(R record);
}
