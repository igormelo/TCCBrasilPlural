package com.igormelo.tccbrasilplural;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 01/11/16.
 */

public class Address {
    @SerializedName("street")
    @Expose
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


}
