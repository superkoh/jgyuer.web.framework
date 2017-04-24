package com.jgyuer.autoconfig.rtenv;

import com.jgyuer.autoconfig.profiles.ProfileConstant;
import com.jgyuer.framework.runtime.RuntimeEnv;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by KOH on 2017/2/5.
 * <p>
 * webFramework
 */
@Profile({ProfileConstant.RT_SCRIPT})
@Component
public class ScriptRuntimeEnv extends RuntimeEnv {
}
