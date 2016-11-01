package com.igormelo.tccbrasilplural;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by root on 01/11/16.
 */

public interface Service {
    @GET("users")
    Call<ArrayList<Users>> getUsers(
            //@Path("id") String id,
            //@Path("name") String name,
            //@Path("email") String email
            //@Path("street") String street
    );
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
