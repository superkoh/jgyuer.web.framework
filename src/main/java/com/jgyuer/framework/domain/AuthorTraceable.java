package com.jgyuer.framework.domain;

public interface AuthorTraceable {
    void setCreateUser(Integer createUser);
    Integer getCreateUser();
    void setUpdateUser(Integer updateUser);
    Integer getUpdateUser();
    void setCreateUserName(String createUserName);
    String getCreateUserName();
    void setUpdateUserName(String updateUserName);
    String getUpdateUserName();
}
