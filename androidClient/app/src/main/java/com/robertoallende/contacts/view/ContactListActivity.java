package com.robertoallende.contacts.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;
import com.robertoallende.contacts.R;
import com.robertoallende.contacts.controller.ContactsController;
import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.events.GetUsersResultEvent;
import com.robertoallende.contacts.events.RemoveUsersResultEvent;
import com.robertoallende.contacts.events.SaveUsersResultEvent;
import com.robertoallende.contacts.view.adapters.ContactsAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private ContactsAdapter mAdapter;
    private static int ADD_CONTACT = 1;
    private static final int TIME_TO_AUTOMATICALLY_DISMISS_ITEM = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initViewItems();

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
        List<User> users = new ArrayList<User>();
        mAdapter = new ContactsAdapter(this, users);
        if (listview != null) {
            listview.setAdapter(mAdapter);
        }
        getContacts();
    }


    public void inflateAdapter(List<User> users) {
        mAdapter.addAll(users);
        ListView listView = (ListView) findViewById(R.id.contact_list);
        final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(listView),
                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onPendingDismiss(ListViewAdapter recyclerView, int position) {

                            }

                            @Override
                            public void onDismiss(ListViewAdapter view, int position) {
                                User user = mAdapter.getUser(position);
                                if (user != null) {
                                    removeContact(user);
                                    mAdapter.remove(position);
                                }
                            }
                        });

        listView.setOnTouchListener(touchListener);
        touchListener.setDismissDelay(TIME_TO_AUTOMATICALLY_DISMISS_ITEM);


        listView.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss();
                } else {
                    openPersonActivity(mAdapter.getUser(position));
                }
            }
        });
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
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetUsersResultEvent eventResult) {
        if (eventResult.isSuccess()) {
            final List<User> users = eventResult.getUsers();
            if (users != null) {
                ListView listview = (ListView)findViewById(R.id.contact_list);
                mAdapter = new ContactsAdapter(this, users);
                if (listview != null) {
                    listview.setAdapter(mAdapter);

                }
                inflateAdapter(users);
            } else {
                displayError("Error fetching data");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RemoveUsersResultEvent eventResult) {
        if (! eventResult.isSuccess()) {
            getContacts();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SaveUsersResultEvent eventResult) {
        if (eventResult.isSuccess()) {
            getContacts();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_CONTACT) {
            if (resultCode == RESULT_OK) {
                User user = (User) data.getSerializableExtra(ContactAddActivity.USER);
                //mAdapter.add(user);
                saveContact(user);
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getContacts() {
        ContactsController controller = ContactsController.getInstance(this);
        controller.getContacts();
    }

    private void saveContact(User user) {

        ContactsController controller = ContactsController.getInstance(this);
        controller.saveContact(user);
    }

    private void removeContact(User user) {
        ContactsController controller = ContactsController.getInstance(this);
        controller.removeContact(user);
    }

}
