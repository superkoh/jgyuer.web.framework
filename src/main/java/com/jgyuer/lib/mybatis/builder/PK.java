package com.jgyuer.lib.mybatis.builder;

import java.lang.annotation.*;

/**
 * Created by KOH on 2017/5/13.
 * <p>
 * pte-server
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PK {
}
