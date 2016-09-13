package com.robertoallende.contacts.entities;

import java.io.Serializable;

public class Name implements Serializable {

    public String title;
    public String first;
    public String last;

    public Name(String first, String last) {
        this.last = last;
        this.first = first;
    }

    public String toString() {

        String firstName = first.substring(0,1).toUpperCase() + first.substring(1);
        String lastName = last.substring(0,1).toUpperCase() + last.substring(1);

        return firstName + " " + lastName;
    }

}
