package com.robertoallende.contacts.events;

public class SaveUsersResultEvent extends ContactsAppEvent{

    public SaveUsersResultEvent(Boolean success) {
        super(success);
    }
}
