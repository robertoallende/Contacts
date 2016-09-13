package com.robertoallende.contacts.controller;

import android.content.Context;

import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.jobs.GetContactsJob;
import com.robertoallende.contacts.jobs.SaveContactJob;

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

    public void saveContact(User user) {
        mJobManager.addJobInBackground(new SaveContactJob(getContext(), user));
    }


}
