package com.gargpiyush.android.useraccountmanagement.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 08:37.
 */
public class UserInfoResponseData {

    @SerializedName("name")
    private String name;

    @SerializedName("profile")
    private UserInfoResponseDataProfile profile;

    public UserInfoResponseData(String name, UserInfoResponseDataProfile profile) {
        this.name = name;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserInfoResponseDataProfile getProfile() {
        return profile;
    }

    public void setProfile(UserInfoResponseDataProfile profile) {
        this.profile = profile;
    }
}