package com.gargpiyush.android.useraccountmanagement.model;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 05:51.
 */
public class LoginRequest {

    private String password;
    private String email;

    public LoginRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
