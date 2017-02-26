package com.jgyuer.framework.security.subject;

import com.jgyuer.framework.lang.BaseObject;

import java.time.LocalDateTime;

/**
 * Created by KOH on 2016/12/19.
 */
public class SystemUser extends BaseObject implements LoginUser {
    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public String getUsername() {
        return "system";
    }

    @Override
    public String getToken() {
        return "system";
    }

    @Override
    public LocalDateTime getTokenExpireTime() {
        return LocalDateTime.now();
    }
}
