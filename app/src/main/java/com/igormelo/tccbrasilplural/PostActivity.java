package com.igormelo.tccbrasilplural;

import android.app.ActionBar;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.tool.DataBindingBuilder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.igormelo.tccbrasilplural.adapters.PostsAdapter;
import com.igormelo.tccbrasilplural.databinding.PostsBinding;
import com.igormelo.tccbrasilplural.fragments.FragmentTwo;
import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Post;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class PostActivity extends AppCompatActivity{
    PostsBinding binding;
    private RecyclerView recyclerView;
    private PostsAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String nome;
    String email;
    String phone;
    String website;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Posts");
        binding = DataBindingUtil.setContentView(this,R.layout.posts);
        recyclerView = (RecyclerView) findViewById(R.id.posts_recycler_view);
        Service service = Service.retrofit.create(Service.class);

        //pega os Intents do <User> por String
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("id");
        nome = bundle.getString("nome");
        email = bundle.getString("email");
        phone = bundle.getString("phone");
        website = bundle.getString("website");

        //chamar o post RXJAVA
        Observable<ArrayList<Post>> call = service.getPostsByUserId(Integer.valueOf(id));
        Subscription subscription = call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(PostActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ArrayList<Post> posts) {
                        adapter = new PostsAdapter(posts, PostActivity.this, new OnItemClickPost() {
                            @Override
                            public void onItemClick(Post post) {
                                Intent intent = new Intent(PostActivity.this, CommentsActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("id", post.getUserId());
                                bundle.putString("postId", post.getId());
                                bundle.putString("nome", nome);
                                bundle.putString("email", email);
                                bundle.putString("phone", phone);
                                bundle.putString("website", website);
                                bundle.putString("title", post.getTitle());
                                bundle.putString("body", post.getBody());
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }

                        });
                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);
                    }
                });

        }
    }
