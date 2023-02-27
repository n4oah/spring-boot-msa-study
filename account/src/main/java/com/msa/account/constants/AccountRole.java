package com.msa.account.constants;

import org.springframework.security.core.GrantedAuthority;

public enum AccountRole implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    AccountRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String getAuthority() {
        return this.getRole();
    }
}
