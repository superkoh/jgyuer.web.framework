package com.jgyuer.framework.exception;

public class NotLoginException extends BizRuntimeException {
    public NotLoginException() {
        super(4031, "not login");
    }
}
