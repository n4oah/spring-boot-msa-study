package com.msa.gateway.constants;
public enum AccountRole  {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    AccountRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
