package com.robertoallende.contacts.entities;

import java.io.Serializable;

// This is used to map the JSON keys to the object by GSON
public class User implements Serializable {

    public String _id;
    public String gender;
    public Name name;
    public Location location;
    public Picture picture;
    public String email;
    public String phone;
    public String cell;
    public Integer __v;

}
