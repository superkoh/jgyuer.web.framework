package com.jgyuer.framework.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Profile(ProfileConstant.RT_JOB)
@Configuration
@EnableScheduling
public class BaseJobProfile {
}
