package com.robertoallende.contacts.jobs;

import android.content.Context;

import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.events.RemoveUsersResultEvent;
import com.robertoallende.contacts.model.ContactsModel;
import org.greenrobot.eventbus.EventBus;

public class RemoveContactJob  extends ContactsAppJob {

    Context mContext;
    User mUser;

    public RemoveContactJob(Context context, User user) {
        mUser = user;
        mContext = context;
    }

    @Override
    public void onRun() throws Throwable {
        ContactsModel contactsModel = new ContactsModel(mContext);
        Boolean result = contactsModel.removeContact(mUser);
        EventBus.getDefault().post(new RemoveUsersResultEvent(result));
    }
}
