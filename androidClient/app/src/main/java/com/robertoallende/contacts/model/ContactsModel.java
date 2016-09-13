package com.robertoallende.contacts.model;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.entities.Users;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsModel {

    private Gson mGson;
    private Retrofit mRetrofit;

    public ContactsModel() {
        mGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.71:8000")
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

        return result.body();
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


