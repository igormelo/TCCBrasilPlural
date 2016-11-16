package com.igormelo.tccbrasilplural;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.igormelo.tccbrasilplural.adapters.UserAdapter;
import com.igormelo.tccbrasilplural.modelos.Users;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Users> data;
    private UserAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Primeira");
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);

        /////// TODO:  Conexão com retrofit
        Service service = Service.retrofit.create(Service.class);

        final RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);


        ////// TODO: Chamar User do JSON
        Call<ArrayList<Users>> call = service.getUsers();
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                if (response.isSuccessful()) {

                    //TODO:  attach ao recyclerView
                    data = response.body();
                    adapter = new UserAdapter(data, MainActivity.this, new OnItemClickListener() {
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
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    recyclerView.setItemAnimator(itemAnimator);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Users>> call, Throwable t) {
                //ERROR
            }
        });
    }
}