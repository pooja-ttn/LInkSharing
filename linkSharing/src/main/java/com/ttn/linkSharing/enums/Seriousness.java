package com.ttn.linkSharing.enums;

public enum Seriousness {
    VERY_SERIOUS("Very_Serious"),
    CASUAL("Casual"),
    SERIOUS("Serous");

    String value;

    Seriousness(String seriousness){
        value=seriousness;

    }

    String getValue(){
        return value;
    }


}
