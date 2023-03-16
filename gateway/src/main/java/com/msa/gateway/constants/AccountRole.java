package com.msa.gateway.constants;
public enum AccountRole  {
    User("User"),
    Admin("Admin");

    private final String role;

    AccountRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
