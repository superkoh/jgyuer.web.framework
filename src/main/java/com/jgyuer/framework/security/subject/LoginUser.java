package com.jgyuer.framework.security.subject;

import java.time.LocalDateTime;

/**
 * Created by KOH on 16/8/4.
 */
public interface LoginUser {
    Integer getId();

    String getUsername();

    String getToken();

    LocalDateTime getTokenExpireTime();
}
