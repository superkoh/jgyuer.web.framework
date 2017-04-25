package com.jgyuer.lib.wechat.bean;

/**
 * Created by KOH on 2017/4/25.
 * <p>
 * webFramework
 */
public class WechatError {
    private Integer errcode;
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
