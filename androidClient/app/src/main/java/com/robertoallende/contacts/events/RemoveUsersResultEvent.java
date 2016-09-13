package com.robertoallende.contacts.events;

public class RemoveUsersResultEvent  extends ContactsAppEvent {

    public RemoveUsersResultEvent(Boolean success) {
        super(success);
    }
}
