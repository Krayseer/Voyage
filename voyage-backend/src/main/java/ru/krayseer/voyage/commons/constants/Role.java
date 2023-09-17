package ru.krayseer.voyage.commons.constants;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER, ADMIN;

    private final String authority;

    Role() {
        this.authority = "ROLE_" + name();
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
