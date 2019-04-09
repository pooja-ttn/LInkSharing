package com.ttn.linkSharing.CO;

public class LoginCO {

    private String userName;

    private String password;
    private  String email;

    public String getEmail() {
        return email;
    }

    public LoginCO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public LoginCO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginCO setPassword(String password) {
        this.password = password;
        return this;
    }
}
