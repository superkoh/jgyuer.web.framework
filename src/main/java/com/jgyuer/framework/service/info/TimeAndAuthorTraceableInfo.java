package com.jgyuer.framework.service.info;

/**
 * Created by zhangyh on 2016/9/29.
 */
public class TimeAndAuthorTraceableInfo extends TimeTraceableInfo {
    private Integer createUser;
    private Integer updateUser;
    private String createUserName;
    private String updateUserName;

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
