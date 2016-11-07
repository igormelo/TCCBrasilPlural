package com.igormelo.tccbrasilplural;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        Service service = Service.retrofit.create(Service.class);

        //Chamar User
        Call<ArrayList<Users>> call = service.getUsers();
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                if (response.isSuccessful()) {
                    //attach ao recyclerView
                    data = response.body();
                    adapter = new UserAdapter(data, MainActivity.this, new OnItemClickListener() {
                        @Override
                        public void onItemClick(Users users) {
                            Intent intent = new Intent(MainActivity.this, PostActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("primeira", users.getId());
                            //bundle.putString("segunda", users.getName());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                    });
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
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