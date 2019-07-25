package com.gargpiyush.android.useraccountmanagement.model;

import java.util.Date;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 05:52.
 */
public class DetailsUpdateRequest {
    private String location;
    private Date birthdate;

    public DetailsUpdateRequest(String location, Date birthdate) {
        this.location = location;
        this.birthdate = birthdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
