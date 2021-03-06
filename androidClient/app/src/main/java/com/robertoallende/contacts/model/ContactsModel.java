package com.robertoallende.contacts.model;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robertoallende.contacts.R;
import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.entities.UserCompare;
import com.robertoallende.contacts.entities.Users;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsModel {

    private Gson mGson;
    private Retrofit mRetrofit;

    public ContactsModel(Context context) {
        mGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

        String url = context.getResources().getString(R.string.server_url);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
    }

    public List<User> getContacts() {
        ContactsApi contactsApi = mRetrofit.create(ContactsApi.class);

        Call<List<User>> call = contactsApi.getUsers();
        Response<List<User>> result = null;
        try {
            result = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result == null) {
            return null;
        }

        List<User> users = result.body();
        Collections.sort(users, new UserCompare());

        return users;
    }

    public Boolean saveContact(User user) {
        ContactsApi contactsApi = mRetrofit.create(ContactsApi.class);
        Call<Boolean> call = contactsApi.createUser(user);
        Response<Boolean> result = null;
        try {
            result = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result == null) {
            return false;
        }
        return result.body();
    }

    public Boolean removeContact(User user) {
        ContactsApi contactsApi = mRetrofit.create(ContactsApi.class);
        Call<Boolean> call = contactsApi.deleteItem(user._id);

        Response<Boolean> result = null;
        try {
            result = call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == null) {
            return false;
        }
        return result.body();
    }

}


