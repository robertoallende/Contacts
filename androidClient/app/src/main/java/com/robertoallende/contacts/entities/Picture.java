package com.robertoallende.contacts.entities;

import java.io.Serializable;

public class Picture implements Serializable {

    public String large;
    public String medium;
    public String thumbnail;

    public Picture() {
        this.large = "";
        this.medium = "";
        this.thumbnail = "";
    }
}
