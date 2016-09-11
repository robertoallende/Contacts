package com.robertoallende.contacts.events;

public abstract class ContactsAppEvent {

    Boolean success;

    public ContactsAppEvent(Boolean success) {
        this.success = success;
    }

    public Boolean isSuccess(){
        return success;
    }

}
