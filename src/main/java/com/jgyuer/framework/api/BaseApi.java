package com.jgyuer.framework.api;

import com.jgyuer.framework.runtime.env.RuntimeEnv;
import com.jgyuer.framework.runtime.env.WebRuntimeEnv;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by KOH on 2017/2/7.
 * <p>
 * webFramework
 */
abstract public class BaseApi {
    @Autowired
    private RuntimeEnv runtimeEnv;

    protected WebRuntimeEnv getEnv() {
        return (WebRuntimeEnv) runtimeEnv;
    }
}
