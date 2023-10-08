package com.yofujitsu.youtubeclone.dao.entities.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}