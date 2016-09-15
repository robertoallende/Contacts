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
        String cityName = "";
        String stateName = "";
        if (city != null && !city.isEmpty() ) {
            cityName = city.substring(0,1).toUpperCase() + city.substring(1);
        }
        if (state != null && !state.isEmpty()) {
            stateName = state.substring(0, 1).toUpperCase() + state.substring(1);
        }

        if (cityName.isEmpty() && stateName.isEmpty()) {
            return "";
        } else if (! cityName.isEmpty() && stateName.isEmpty()) {
            return cityName;
        } else if (cityName.isEmpty() && ! stateName.isEmpty()) {
            return stateName;
        }

        return cityName + ", " + stateName;
    }

}
