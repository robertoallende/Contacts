package com.robertoallende.contacts.jobs;

import android.content.Context;

import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.events.SaveUsersResultEvent;
import com.robertoallende.contacts.model.ContactsModel;
import org.greenrobot.eventbus.EventBus;


public class SaveContactJob  extends ContactsAppJob {

    Context mContext;
    User mUser;

    public SaveContactJob(Context context, User user) {
        mUser = user;
        mContext = context;
    }

    @Override
    public void onRun() throws Throwable {
        ContactsModel contactsModel = new ContactsModel();
        Boolean result = contactsModel.saveContact(mUser);
        EventBus.getDefault().post(new SaveUsersResultEvent(result));
    }
}
