package com.jgyuer.framework.api.response;

import com.jgyuer.framework.exception.BizRuntimeException;
import com.jgyuer.framework.exception.BizException;

public class ErrorRes extends BizRes {
    private Integer ok = -1;
    private String msg = "未知错误";

    public ErrorRes() {
    }

    public ErrorRes(Integer ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public ErrorRes(BizException e) {
        this.ok = e.getCode();
        this.msg = e.getMessage();
    }

    public ErrorRes(BizRuntimeException e) {
        this.ok = e.getCode();
        this.msg = e.getMessage();
    }

    public Integer getOk() {
        return ok;
    }

    public void setOk(Integer ok) {
        this.ok = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
