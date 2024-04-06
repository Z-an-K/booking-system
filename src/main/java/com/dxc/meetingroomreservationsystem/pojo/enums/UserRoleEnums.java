package com.dxc.meetingroomreservationsystem.pojo.enums;

public enum UserRoleEnums {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;
    UserRoleEnums(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
