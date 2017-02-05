package com.jgyuer.framework.runtime.env;

import com.jgyuer.framework.profile.ProfileConstant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({ProfileConstant.RT_JOB})
@Component
public class JobRuntimeEnv extends RuntimeEnv {
}
