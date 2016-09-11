package com.robertoallende.contacts.controller;

import android.content.Context;

import com.birbit.android.jobqueue.JobManager;
import com.robertoallende.contacts.ContactsApp;

public class ContactsAppController {

    protected JobManager mJobManager;
    private Context mContext;

    public ContactsAppController(Context context) {
        mContext = context;
        mJobManager = ContactsApp.getInstance().getJobManager();
    }

    public Context getContext() {
        return mContext;
    }

}
