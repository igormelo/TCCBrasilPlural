package com.igormelo.tccbrasilplural;

import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Post;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * SERVIÇO QUE CONECTA COM O SERVIDOR/JSON
 */
public interface Service {
    @GET("users")
    Call<ArrayList<Users>> getUsers();
            //@Path("id") String id,
            //@Path("name") String name,
            //@Path("email") String email
            //@Path("street") String street
    @GET("posts")
    Call<ArrayList<Post>> getPostsByUserId(@Query("userId") int id);

    @GET("comments")
    Call<ArrayList<Comentarios>> getCommentsByPostId(@Query("postId") int id);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
