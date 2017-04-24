package com.jgyuer.framework.runtime;

import com.jgyuer.framework.security.subject.LoginUser;
import com.jgyuer.framework.security.subject.SystemUser;

public class RuntimeEnv {
    private LoginUser loginUser;

    public RuntimeEnv() {
        this.loginUser = new SystemUser();
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }
}
