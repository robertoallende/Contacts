package com.robertoallende.contacts.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.robertoallende.contacts.R;
import com.robertoallende.contacts.entities.User;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
            ImageView imageView = (ImageView) findViewById(R.id.profile_contact_photo);
            if (user.picture != null) {
                String imageUrl = "";
                if (! user.picture.large.isEmpty()) {
                    imageUrl = user.picture.large;
                    Transformation transformation = new RoundedTransformationBuilder()
                            .borderColor(Color.WHITE)
                            .borderWidthDp(1)
                            .cornerRadiusDp(140)
                            .oval(false)
                            .build();

                    Picasso.with(this).load(imageUrl).transform(transformation).into(imageView);
                } else {
                    Picasso.with(this).load(R.drawable.default_user).into(imageView);
                }
            }

            if (user.name != null && personNameView != null) {
                    personNameView.setText(user.name.toString());
                    setTitle(user.name.toString());
            }
            if (user.location != null && personLocationView != null) {
                String location = user.location.toString();
                if (location.isEmpty()) {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.person_location_layout);
                    if (layout != null) {
                        layout.setVisibility(View.GONE);
                    }
                }  else {
                    personLocationView.setText(location);
                }

            }
            if (user.email != null && personEmailView != null) {
                personEmailView.setText(user.email);
            }
            if (user.phone != null && personPhoneView != null) {
                if (user.phone.isEmpty()) {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.phone_location_layout);
                    if (layout != null) {
                        layout.setVisibility(View.GONE);
                    }
                } else {
                    personPhoneView.setText(user.phone);
                }
            }
            if (user.cell != null && personCellView != null) {
                if (user.cell.isEmpty()) {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.smartphone_location_layout);
                    if (layout != null) {
                        layout.setVisibility(View.GONE);
                    }
                }
                personCellView.setText(user.cell);
            }
        }

    }


}
