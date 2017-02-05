package com.jgyuer.framework.persistence.mybatis.exception;

import com.jgyuer.framework.exception.BizException;

/**
 * Created by KOH on 2016/10/23.
 */
public class RecordNotFoundException extends BizException {
    public RecordNotFoundException(String name) {
        super(name + " not found");
    }
}
