package com.ttn.linkSharing.enums;

public enum Visibility {
    PUBLIC("Public"),
    PRIVATE("Private");
    String value;
    Visibility(String visibility){
        value=visibility;
    }

    public String getValue(){
        return value;
    }
}

