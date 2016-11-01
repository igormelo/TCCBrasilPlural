package com.igormelo.tccbrasilplural;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Users> data;
    private Adapters adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        Service service = Service.retrofit.create(Service.class);
        Call<ArrayList<Users>> call = service.getUsers();
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                if (response.isSuccessful()) {
                    data = response.body();

                    //onde os dados são carregados
                    adapter = new Adapters(data, MainActivity.this, new OnUserItemClickListener() {
                        @Override
                        public void onItemClick(Users user) {
                            Toast.makeText(MainActivity.this, "TESTE: " + user, Toast.LENGTH_SHORT).show();
                        }
                    });

                    //attach ao recyclerView
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Erro: " + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                    Log.e("ERRO", "Erro: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure:" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERRO", "Failure:" + t.getMessage());
            }
        });


    }

}
