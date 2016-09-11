package com.robertoallende.contacts.controller;

import android.content.Context;

import com.robertoallende.contacts.jobs.GetContactsJob;

public class ContactsController extends ContactsAppController {

    private static ContactsController instance;

    public ContactsController(Context context) {
        super(context);
    }

    public synchronized static ContactsController getInstance(Context context) {
        if(instance == null) {
            instance = new ContactsController(context);
        }
        return instance;
    }

    public void getContacts() {
        mJobManager.addJobInBackground(new GetContactsJob(getContext()));
    }


}
