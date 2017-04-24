package com.jgyuer.autoconfig.rtenv;

import com.jgyuer.autoconfig.profiles.ProfileConstant;
import com.jgyuer.framework.runtime.RuntimeEnv;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({ProfileConstant.RT_JOB})
@Component
public class JobRuntimeEnv extends RuntimeEnv {
}
