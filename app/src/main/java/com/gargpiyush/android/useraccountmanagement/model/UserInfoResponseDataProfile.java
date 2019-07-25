package com.gargpiyush.android.useraccountmanagement.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 08:38.
 */
public class UserInfoResponseDataProfile {

    @SerializedName("birthdate")
    private Date birthday;

    @SerializedName("location")
    private String location;

    public UserInfoResponseDataProfile(Date birthday, String location) {
        this.birthday = birthday;
        this.location = location;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
