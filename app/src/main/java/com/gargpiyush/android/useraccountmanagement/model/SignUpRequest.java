package com.gargpiyush.android.useraccountmanagement.model;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 05:51.
 */
public class SignUpRequest {

    private String name;
    private String password;
    private String password2;
    private String email;

    public SignUpRequest(String name, String password, String password2, String email) {
        this.name = name;
        this.password = password;
        this.password2 = password2;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
