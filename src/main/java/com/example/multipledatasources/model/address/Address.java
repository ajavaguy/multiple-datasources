package com.example.multipledatasources.model.address;

import com.example.multipledatasources.datasources.address.AddressJSON;

public class Address {
    private Integer id;
    private String lane1;
    private String lane2;
    private String state;
    private Integer zip;

    public Address() {}

    public Address(Integer id, String lane1, String lane2, String state, Integer zip) {
        this.id = id;
        this.lane1 = lane1;
        this.lane2 = lane2;
        this.state = state;
        this.zip = zip;
    }
    public static Address of(AddressJSON addressJSON) {
        return new Address(addressJSON.getId(), addressJSON.getLane1(), addressJSON.getLane2(), addressJSON.getState(), addressJSON.getZip());
    }

    public Integer getId() {
        return id;
    }

    public String getLane1() {
        return lane1;
    }

    public String getLane2() {
        return lane2;
    }

    public String getState() {
        return state;
    }

    public Integer getZip() {
        return zip;
    }
}
