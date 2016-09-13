package com.robertoallende.contacts.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.robertoallende.contacts.R;
import com.robertoallende.contacts.entities.User;

public class ContactActivity extends AppCompatActivity {

    private static String PERSON = "person";

    public static Intent makeIntent(Context context, User person) {
        Intent intent = new Intent(context, ContactActivity.class);
        intent.putExtra(PERSON, person);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initViewItems();
    }

    public void initViewItems() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView personNameView = (TextView) findViewById(R.id.person_name);
        TextView personLocationView = (TextView) findViewById(R.id.person_location);
        TextView personEmailView = (TextView) findViewById(R.id.person_email);
        TextView personPhoneView = (TextView) findViewById(R.id.person_phone);
        TextView personCellView = (TextView) findViewById(R.id.person_cell);

        User user = (User) getIntent().getExtras().getSerializable(PERSON);
        if (user != null) {
            if (user.name != null && personNameView != null) {
                    personNameView.setText(user.name.toString());
            }
            if (user.location != null && personLocationView != null) {
                personLocationView.setText(user.location.toString());
            }
            if (user.email != null && personEmailView != null) {
                personEmailView.setText(user.email);
            }
            if (user.phone != null && personPhoneView != null) {
                personPhoneView.setText(user.phone);
            }
            if (user.cell != null && personCellView != null) {
                personCellView.setText(user.cell);
            }
        }

    }


}
