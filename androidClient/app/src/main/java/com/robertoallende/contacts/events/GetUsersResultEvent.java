package com.robertoallende.contacts.events;

import com.robertoallende.contacts.entities.User;


import java.util.List;

public class GetUsersResultEvent extends ContactsAppEvent{

    private List<User> mUsers;

    public GetUsersResultEvent(Boolean success, List<User> users) {
        super(success);
        this.mUsers = users;
    }

    public List<User> getUsers() {
        return mUsers;
    }

}
