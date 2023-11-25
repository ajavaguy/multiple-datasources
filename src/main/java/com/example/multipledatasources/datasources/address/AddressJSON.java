package com.example.multipledatasources.datasources.address;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressJSON {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("lane1")
    private String lane1;
    @JsonProperty("lane2")
    private String lane2;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zip")
    private Integer zip;

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
