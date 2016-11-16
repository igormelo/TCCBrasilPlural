package com.igormelo.tccbrasilplural;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.igormelo.tccbrasilplural.adapters.CommentsAdapter;
import com.igormelo.tccbrasilplural.adapters.ViewPagerAdapter;
import com.igormelo.tccbrasilplural.fragments.FragmentOne;
import com.igormelo.tccbrasilplural.fragments.FragmentTwo;
import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class CommentsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String postId;
    String nome;
    String email;
    String phone;
    String website;
    String title;
    String body;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        //Pega os Intent do <Post> por String
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        postId = bundle.getString("postId");
        nome = bundle.getString("nome");
        email = bundle.getString("email");
        phone = bundle.getString("phone");
        website = bundle.getString("website");
        title = bundle.getString("title");
        body = bundle.getString("body");

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentOne.newInstance(nome,email,phone,website), "Informações");
        adapter.addFragment(FragmentTwo.newInstance(postId,title,body), "Comentários");
        viewPager.setAdapter(adapter);
    }



}
