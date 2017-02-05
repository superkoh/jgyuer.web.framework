package com.jgyuer.framework.security.service;

import com.jgyuer.framework.security.subject.LoginUser;
import org.hibernate.validator.constraints.NotBlank;

public interface LoginUserService {
    LoginUser getUserByToken(@NotBlank String token);
}
