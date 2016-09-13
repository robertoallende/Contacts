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

public class ContactsActivity extends AppCompatActivity {

    private ContactsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        Intent intent = PersonActivity.makeIntent(this, user);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetUsersResultEvent eventResult) {
        if (eventResult.isSuccess()) {
            final List<User> users = eventResult.getUsers();
            mAdapter.addAll(users);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (! EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

}
