package com.robertoallende.contacts.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.robertoallende.contacts.R;
import com.robertoallende.contacts.controller.ContactsController;
import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.events.GetUsersResultEvent;
import com.robertoallende.contacts.view.adapters.ContactsAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private ContactsAdapter mAdapter;
    private static int ADD_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initViewItems();
        initActivityData();

        EventBus.getDefault().register(this);
    }

    public void initViewItems() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContactAddActivity();
            }
        });

        ListView listview = (ListView)findViewById(R.id.contact_list);
        ArrayList<User> users = new ArrayList<User>();
        mAdapter = new ContactsAdapter(this, users);
        listview.setAdapter(mAdapter);
    }

    public void initActivityData() {
        ContactsController controller = ContactsController.getInstance(this);
        controller.getContacts();
    }

    public void openPersonActivity(User user) {
        Intent intent = ContactActivity.makeIntent(this, user);
        startActivity(intent);
    }

    public void openContactAddActivity() {
        Intent intent = ContactAddActivity.makeIntent(this);
        startActivityForResult(intent, ADD_CONTACT);
    }

    public void displayError(String errorMsg) {
        View view = (View) this.findViewById(android.R.id.content).getRootView();
        Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetUsersResultEvent eventResult) {
        if (eventResult.isSuccess()) {
            final List<User> users = eventResult.getUsers();
            if (users != null) {
                mAdapter.addAll(users);
            } else {
                displayError("Error fetching data");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_CONTACT) {
            if (resultCode == RESULT_OK) {
                User user = (User) data.getSerializableExtra(ContactAddActivity.USER);
                mAdapter.add(user);
                ContactsController controller = ContactsController.getInstance(this);
                controller.saveContact(user);
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (! EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

}
