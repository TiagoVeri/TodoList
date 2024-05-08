package com.study.user.enums;

public enum Roles {

    USER("USER_PROFILE"),
    ADMIN("ADMIN_PROFILE");

    private final String role;
    Roles(String newRole){
        this.role = newRole;
    }

    public String getRole(){
        return this.role;
    }
}
