package com.robertoallende.contacts.model;

import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.entities.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ContactsApi {

    @GET("/users")
    Call<List<User>> getUsers();

    @POST("/users/new")
    Call<Boolean> createUser(@Body User user);

    @DELETE("/users/{id}")
    Call<Boolean> deleteItem(@Path("id") String itemId);

}
