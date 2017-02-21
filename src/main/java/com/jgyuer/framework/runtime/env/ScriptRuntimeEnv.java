package com.jgyuer.framework.runtime.env;

import com.jgyuer.framework.profile.ProfileConstant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by KOH on 2017/2/5.
 * <p>
 * webFramework
 */
@Profile({ProfileConstant.RT_SCRIPT})
@Component
public class ScriptRuntimeEnv {
}
