package com.jgyuer.lib.wechat.exception;

import com.jgyuer.framework.exception.BizException;

/**
 * Created by KOH on 2017/4/25.
 * <p>
 * webFramework
 */
public class WechatSnsException extends BizException {
    public WechatSnsException() {
    }

    public WechatSnsException(String message) {
        super(message);
    }

    public WechatSnsException(int code, String message) {
        super(code, message);
    }
}
