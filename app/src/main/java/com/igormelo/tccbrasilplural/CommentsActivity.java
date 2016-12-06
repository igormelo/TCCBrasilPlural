package com.igormelo.tccbrasilplural;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.igormelo.tccbrasilplural.adapters.ViewPagerAdapter;
import com.igormelo.tccbrasilplural.fragments.FragmentOne;
import com.igormelo.tccbrasilplural.fragments.FragmentTwo;


public class CommentsActivity extends AppCompatActivity {
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
