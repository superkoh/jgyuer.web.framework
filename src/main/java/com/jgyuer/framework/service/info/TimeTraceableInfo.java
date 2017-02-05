package com.jgyuer.framework.service.info;

import com.jgyuer.framework.lang.BaseObject;

import java.time.LocalDateTime;

public class TimeTraceableInfo extends BaseObject {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
