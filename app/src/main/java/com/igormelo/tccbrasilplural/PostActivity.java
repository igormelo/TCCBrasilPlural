package com.igormelo.tccbrasilplural;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.igormelo.tccbrasilplural.adapters.PostsAdapter;
import com.igormelo.tccbrasilplural.fragments.FragmentTwo;
import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Post;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;


public class PostActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private ArrayList<Post> post;
    private PostsAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);
        recyclerView = (RecyclerView) findViewById(R.id.posts_recycler_view);
        Service service = Service.retrofit.create(Service.class);

        //chamar o post
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String primeira = bundle.getString("primeira");
        //String segunda = bundle.getString("segunda");
        Call<ArrayList<Post>> call = service.getPostsByUserId(Integer.valueOf(primeira));
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                if(response.isSuccessful()) {
                    post = response.body();
                    adapter = new PostsAdapter(post, PostActivity.this, new OnItemClickPost() {
                        @Override
                        public void onItemClick(Post post) {
                            Intent intent = new Intent(PostActivity.this,CommentsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("segunda", post.getUserId());
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }

                    });
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(PostActivity.this, "oi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(PostActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
