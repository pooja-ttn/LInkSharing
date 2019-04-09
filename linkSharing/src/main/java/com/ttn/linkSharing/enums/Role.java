package com.ttn.linkSharing.enums;

public enum Role {
    ADMIN("Admin"),
    USER("User");

    String value;
    Role(String role){
        value=role;
    }

    String getValue(){
        return value;
    }
}
