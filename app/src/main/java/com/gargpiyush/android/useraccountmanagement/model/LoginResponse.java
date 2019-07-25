package com.gargpiyush.android.useraccountmanagement.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 06:56.
 */

public class LoginResponse implements Serializable {

    @SerializedName("message")
    private String msg;

    @SerializedName("data")
    private LoginResponseData loginResponseData;

    public LoginResponse(String msg, LoginResponseData loginResponseData) {
        this.msg = msg;
        this.loginResponseData = loginResponseData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginResponseData getLoginResponseData() {
        return loginResponseData;
    }

    public void setLoginResponseData(LoginResponseData loginResponseData) {
        this.loginResponseData = loginResponseData;
    }
}
