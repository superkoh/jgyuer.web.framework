package com.jgyuer.framework.exception;

public class NeedGuestException extends BizRuntimeException {
    public NeedGuestException() {
        super(4032, "need guest");
    }
}
