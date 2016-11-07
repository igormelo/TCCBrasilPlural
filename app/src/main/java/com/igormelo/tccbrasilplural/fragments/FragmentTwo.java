package com.igormelo.tccbrasilplural.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igormelo.tccbrasilplural.CommentsActivity;
import com.igormelo.tccbrasilplural.OnItemClickComments;
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.Service;
import com.igormelo.tccbrasilplural.adapters.CommentsAdapter;
import com.igormelo.tccbrasilplural.modelos.Comentarios;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;

/**
 * Created by root on 07/11/16.
 */

public class FragmentTwo extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CommentsAdapter adapter;
    private ArrayList<Comentarios> data;

    public FragmentTwo() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

    return view;
    }
}
