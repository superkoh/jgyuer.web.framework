package com.jgyuer.framework.persistence.mybatis.record;

import java.time.LocalDateTime;

public interface TimeTraceable {
    LocalDateTime getCreateTime();

    void setCreateTime(LocalDateTime createTime);

    LocalDateTime getUpdateTime();

    void setUpdateTime(LocalDateTime updateTime);
}
