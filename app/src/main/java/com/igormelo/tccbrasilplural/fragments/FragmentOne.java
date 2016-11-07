package com.igormelo.tccbrasilplural.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igormelo.tccbrasilplural.CommentsActivity;
import com.igormelo.tccbrasilplural.OnItemClickComments;
import com.igormelo.tccbrasilplural.OnItemClickListener;
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.Service;
import com.igormelo.tccbrasilplural.adapters.CommentsAdapter;
import com.igormelo.tccbrasilplural.adapters.UserAdapter;
import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentOne extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Users> data;
    private UserAdapter adapter;

    public FragmentOne(){
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);




        return view;
    }

}

