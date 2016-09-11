package com.robertoallende.contacts.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.robertoallende.contacts.R;
import com.robertoallende.contacts.controller.ContactsController;
import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.events.GetUsersResultEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ContactsActivity extends AppCompatActivity {

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
    }

    public void initActivityData() {
        ContactsController controller = ContactsController.getInstance(this);
        controller.getContacts();
    }

    @Subscribe
    public void onEvent(GetUsersResultEvent eventResult) {
        if (eventResult.isSuccess()) {
            String newContent = "";
            for (User user: eventResult.getUsers()) {
                newContent += user.name.first + " " + user.name.last + "\n";
            }
            final String content = newContent;
            runOnUiThread(new Runnable() {
                public void run() {
                    TextView view = (TextView) findViewById(R.id.content_contacts_text);
                    view.setText(content);
                }
            });

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
