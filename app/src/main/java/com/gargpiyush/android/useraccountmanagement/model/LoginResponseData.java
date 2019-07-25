package com.gargpiyush.android.useraccountmanagement.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 06:56.
 */
public class LoginResponseData implements Serializable {

    @SerializedName("user_token")
    private String uToken;

    @SerializedName("api_token")
    private String aToken;

    public LoginResponseData(String uToken, String aToken) {
        this.uToken = uToken;
        this.aToken = aToken;
    }

    public String getuToken() {
        return uToken;
    }

    public void setuToken(String uToken) {
        this.uToken = uToken;
    }

    public String getaToken() {
        return aToken;
    }

    public void setaToken(String aToken) {
        this.aToken = aToken;
    }
}
