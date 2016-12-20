package com.igormelo.tccbrasilplural;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.igormelo.tccbrasilplural.adapters.UserAdapter;
import com.igormelo.tccbrasilplural.databinding.ActivityMainBinding;
import com.igormelo.tccbrasilplural.databinding.RowUsersBinding;
import com.igormelo.tccbrasilplural.modelos.Users;
import com.minimize.android.rxrecycleradapter.RxDataSource;
import com.minimize.android.rxrecycleradapter.ViewHolderInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private UserAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().setTitle("Primeira");


        List<ViewHolderInfo> viewHolderInfoList = new ArrayList<>();
        viewHolderInfoList.add(new ViewHolderInfo(R.layout.row_users, 1));

        binding.cardRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Service service = Service.retrofit.create(Service.class);

        final RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);


        Observable<ArrayList<Users>> call = service.getUsers()//Trasmite os dados que ele pegou do retrofit(JSON)
        .subscribeOn(Schedulers.newThread());//O operador subscribeOn cria uma nova thread
                call.observeOn(AndroidSchedulers.mainThread())//observeOn :Thread que o OBSERVADOR vai executar UI(MainThread é a thread de UI(principal))
                .subscribe(new Observer<ArrayList<Users>>() {//Metodo subscribe: Atribui(Inscreve) o OBSERVADOR ao observable
                               @Override
                               public void onCompleted() { // Chamado quando o observable nao tem mais dados para emitir(acabaram os dados, pronto, ele cai aqui)
                                   Toast.makeText(MainActivity.this, "No data to show", Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onError(Throwable e) {//EXECUTA quando observable encontra erros(ex: sem internet)
                               }

                               @Override
                               public void onNext(ArrayList<Users> users) {//EXECUTA quando o observable emitir os dados
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
                                   binding.cardRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                                   binding.cardRecyclerView.setAdapter(adapter);

                               }
                });

    }
}