package com.jgyuer.framework.exception;

public class PermissionDeniedException extends BizException {
    public PermissionDeniedException() {
        super("permission denied!");
    }
}
