package com.gargpiyush.android.useraccountmanagement.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 05:52.
 */
public class UserInfoResponse {

    @SerializedName("data")
    private  UserInfoResponseData data;

    @SerializedName("message")
    private String message;

    public UserInfoResponse(UserInfoResponseData data, String message) {
        this.data = data;
        this.message = message;
    }

    public UserInfoResponseData getData() {
        return data;
    }

    public void setData(UserInfoResponseData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
