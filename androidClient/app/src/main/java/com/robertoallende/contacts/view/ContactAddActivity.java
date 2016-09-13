package com.robertoallende.contacts.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.robertoallende.contacts.R;
import com.robertoallende.contacts.entities.User;

import org.w3c.dom.Text;

public class ContactAddActivity extends AppCompatActivity {

    public static String USER = "USER";

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, ContactAddActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        initViewItems();
    }

    public void initViewItems() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.create_user_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAndClose();
            }
        });
    }

    public void getDataAndClose() {
        String firstName, lastName, city, state, mail, phone, cell;

        TextInputEditText firstNameView = (TextInputEditText) findViewById(R.id.add_first_name);
        TextInputEditText lastNameView = (TextInputEditText) findViewById(R.id.add_last_name);
        TextInputEditText cityView = (TextInputEditText) findViewById(R.id.add_city);
        TextInputEditText stateView = (TextInputEditText) findViewById(R.id.add_state);
        TextInputEditText mailView = (TextInputEditText) findViewById(R.id.add_mail);
        TextInputEditText phoneView = (TextInputEditText) findViewById(R.id.add_phone);
        TextInputEditText cellView = (TextInputEditText) findViewById(R.id.add_cell);

        firstName = firstNameView.getText().toString();
        if(firstName == null || firstName.isEmpty()) {
            displayError("Error: must set a first name.");
            return;
        }
        lastName = lastNameView.getText().toString();
        if(lastName == null || lastName.isEmpty()) {
            displayError("Error: must set a last name.");
            return;
        }

        mail = mailView.getText().toString();
        if(mail == null || mail.isEmpty()) {
            displayError("Error: must set a mail.");
            return;
        }

        phone = phoneView.getText().toString();
        cell = cellView.getText().toString();
        city = cityView.getText().toString();
        state = stateView.getText().toString();

        User user = new User(firstName, lastName, mail, phone, cell, city, state);

        Intent output = new Intent();
        output.putExtra(USER, user);
        setResult(RESULT_OK, output);
        finish();
    }


    public void displayError(String errorMsg) {
        View view = (View) this.findViewById(android.R.id.content).getRootView();
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }


}
