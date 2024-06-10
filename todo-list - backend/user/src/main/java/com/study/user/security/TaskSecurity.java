package com.study.user.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class TaskSecurity {

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuarioId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        Object usuarioId = jwt.getClaim("id");

        if (usuarioId == null) {
            return null;
        }

        return Long.valueOf(usuarioId.toString());
    }
}
