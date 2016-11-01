package com.igormelo.tccbrasilplural;

/**
 * Created by root on 01/11/16.
 */

public class Users {
    private String id;
    private String name;
    private String email;
    Address address = new Address();

    public String getId() {
        return "ID :" + id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
