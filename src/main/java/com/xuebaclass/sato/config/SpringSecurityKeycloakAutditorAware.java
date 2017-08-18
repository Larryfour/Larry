/*
 * Copyright (C) 2016 xuebaclass.com - All Rights Reserved.
 */

package com.xuebaclass.sato.config;

import static com.xuebaclass.sato.common.Constant.*;
import static java.util.Objects.isNull;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by zhaojin on 17-3-27.
 */
public class SpringSecurityKeycloakAutditorAware implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();

        // 目前 akka 提取 spring SecurityContext 有问题

        if (isNull(context)) {
            return NO_CONTEXT;
        }

        Authentication authentication = context.getAuthentication();
        // 目前 akka 提取 spring SecurityContext 有问题

        if (isNull(authentication)) {
            return NO_USER;
        }

        String name = authentication.getName();

        if (KeycloakAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return KEYCLOAK_USER_PREFIX + name; // keycloak sato username
        }

        return UNKNOWN_USER_PREFIX + name;
    }

    public String getCurrentAuditorName() {
        SecurityContext context = SecurityContextHolder.getContext();

        // 目前 akka 提取 spring SecurityContext 有问题

        if (isNull(context)) {
            return NO_USER_NAME;
        }

        Authentication authentication = context.getAuthentication();
        // 目前 akka 提取 spring SecurityContext 有问题

        if (isNull(authentication)) {
            return NO_USER_NAME;
        }

        String name = authentication.getName();

        if (KeycloakAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return name; // keycloak sato username
        }

        return name;
    }

}
