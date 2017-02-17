package com.jgyuer.framework.domain;

import java.time.LocalDateTime;

public interface TimeTraceable {
    LocalDateTime getCreateTime();

    void setCreateTime(LocalDateTime createTime);

    LocalDateTime getUpdateTime();

    void setUpdateTime(LocalDateTime updateTime);
}
