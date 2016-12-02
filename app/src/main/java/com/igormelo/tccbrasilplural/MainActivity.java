package com.igormelo.tccbrasilplural;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.igormelo.tccbrasilplural.adapters.UserAdapter;
import com.igormelo.tccbrasilplural.databinding.ActivityMainBinding;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;
import rx.Observable;
import rx.Subscription;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().setTitle("Primeira");
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);

        Service service = Service.retrofit.create(Service.class);

        final RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);


        Observable<ArrayList<Users>> call = service.getUsers();
        Subscription subscription = call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Users>>() {
                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onNext(ArrayList<Users> users) {
                                   adapter = new UserAdapter(users, MainActivity.this, new OnItemClickListener() {
                                       @Override
                                       public void onItemClick(Users users) {
                                           Intent intent = new Intent(MainActivity.this, PostActivity.class);
                                           Bundle bundle = new Bundle();
                                           bundle.putString("id", users.getId());
                                           bundle.putString("nome", users.getName());
                                           bundle.putString("email", users.getEmail());
                                           bundle.putString("phone", users.getPhone());
                                           bundle.putString("website", users.getWebsite());
                                           intent.putExtras(bundle);
                                           startActivity(intent);
                                       }
                                   });

                                   mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                   binding.cardRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                                   binding.cardRecyclerView.setItemAnimator(itemAnimator);
                                   binding.cardRecyclerView.setAdapter(adapter);
                               }
                });

    }
}