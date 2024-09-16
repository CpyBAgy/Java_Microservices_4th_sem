package com.CpyBAgy.javarush.Entities;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    @Override
    public String getAuthority() { return "ROLE_" + this; }

    @Override
    public String toString() { return this.role; }
}
