package com.jgyuer.framework.security.subject;

import com.jgyuer.framework.lang.BaseObject;

import java.time.LocalDateTime;

/**
 * Created by KOH on 2016/12/29.
 */
public class GuestUser extends BaseObject implements LoginUser {
    @Override
    public Integer getId() {
        return 0;
    }

    @Override
    public String getUsername() {
        return "guest";
    }

    @Override
    public String getToken() {
        return "guest";
    }

    @Override
    public LocalDateTime getTokenExpireTime() {
        return LocalDateTime.now();
    }
}
