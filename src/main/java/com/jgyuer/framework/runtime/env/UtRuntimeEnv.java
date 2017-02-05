package com.jgyuer.framework.runtime.env;


import com.jgyuer.framework.profile.ProfileConstant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(ProfileConstant.RT_UT)
@Component
public class UtRuntimeEnv extends RuntimeEnv {
}
