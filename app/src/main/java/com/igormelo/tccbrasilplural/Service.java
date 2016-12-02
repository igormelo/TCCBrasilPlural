package com.igormelo.tccbrasilplural;

import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Post;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * SERVIÇO QUE CONECTA COM O SERVIDOR/JSON
 */
public interface Service {
    @GET("users")
    Observable<ArrayList<Users>> getUsers();
            //@Path("id") String id,
            //@Path("name") String name,
            //@Path("email") String email
            //@Path("street") String street
    @GET("posts")
    Observable<ArrayList<Post>> getPostsByUserId(@Query("userId") int id);

    @GET("comments")
    Observable<ArrayList<Comentarios>> getCommentsByPostId(@Query("postId") int id);


    RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(rxAdapter)
            .build();

}
