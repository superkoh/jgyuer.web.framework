package com.jgyuer.framework.script;

import com.jgyuer.autoconfig.profiles.ProfileConstant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(ProfileConstant.RT_SCRIPT)
@Component
public @interface Script {
    String value() default "";
}
