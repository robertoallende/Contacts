package com.robertoallende.contacts.entities;

import java.util.Comparator;

public class UserCompare implements Comparator<User> {

    @Override
    public int compare(User obj1, User obj2) {
        return obj1.name.toString().compareToIgnoreCase(obj2.name.toString());
    }
}