package com.robertoallende.contacts.entities;


import java.io.Serializable;

public class Location implements Serializable {

    public String street;
    public String city;
    public String state;
    public Integer zip;

    public Location(String city, String state) {
        this.state = state;
        this.city = city;
    }

    public String toString() {

        String cityName = city.substring(0,1).toUpperCase() + city.substring(1);
        String stateName = state.substring(0,1).toUpperCase() + state.substring(1);

        return cityName + ", " + stateName;
    }

}
