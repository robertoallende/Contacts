package com.robertoallende.contacts.jobs;

import android.content.Context;

import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.entities.Users;
import com.robertoallende.contacts.events.GetUsersResultEvent;
import com.robertoallende.contacts.model.ContactsModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class GetContactsJob extends ContactsAppJob {

    private Context mContext;

    public GetContactsJob(Context context) {
        mContext = context;
    }

    @Override
    public void onRun() throws Throwable {
        ContactsModel contactsModel = new ContactsModel();
        List<User> users = contactsModel.getContacts();
        Boolean result = false;
        if (users != null && users.size() > 0) {
            result = true;
        } else if (users == null) {
            EventBus.getDefault().post(new GetUsersResultEvent(false, users));
        }
        EventBus.getDefault().post(new GetUsersResultEvent(true, users));


    }
}
