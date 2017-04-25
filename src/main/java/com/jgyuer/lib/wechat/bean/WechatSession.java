package com.jgyuer.lib.wechat.bean;

/**
 * Created by KOH on 2017/4/25.
 * <p>
 * webFramework
 */
public class WechatSession {
    private Integer expiresIn;
    private String openid;
    private String sessionKey;

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
