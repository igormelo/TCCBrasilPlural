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
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.igormelo.tccbrasilplural.CommentsActivity;
import com.igormelo.tccbrasilplural.MainActivity;
import com.igormelo.tccbrasilplural.OnItemClickComments;
import com.igormelo.tccbrasilplural.OnItemClickListener;
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.Service;
import com.igormelo.tccbrasilplural.adapters.CommentsAdapter;
import com.igormelo.tccbrasilplural.adapters.InfoAdapter;
import com.igormelo.tccbrasilplural.adapters.UserAdapter;
import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentOne extends Fragment {
    String nome;
    String emaill;
    String phonee;
    String websitee;

    public FragmentOne(){
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActivity().getActionBar().hide();


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Service service = Service.retrofit.create(Service.class);
        final View view = inflater.inflate(R.layout.fragment_one, container, false);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);


        if(getArguments() != null) {
            nome = getArguments().getString("nome");
            emaill = getArguments().getString("email");
            phonee = getArguments().getString("phone");
            websitee = getArguments().getString("website");
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView email = (TextView) view.findViewById(R.id.email);
        TextView phone = (TextView) view.findViewById(R.id.phone);
        TextView website = (TextView) view.findViewById(R.id.website);
        name.setText(nome);
        email.setText(emaill);
        phone.setText(phonee);
        website.setText(websitee);

       // Toast.makeText(getActivity(), "name: " + nome, Toast.LENGTH_SHORT).show();



        return view;
    }

    public static FragmentOne newInstance(String nome, String email, String phone, String website) {
        FragmentOne f = new FragmentOne();
        Bundle args = new Bundle();

        args.putString("nome", nome);
        args.putString("email", email);
        args.putString("phone", phone);
        args.putString("website", website);
        f.setArguments(args);

        return f;
    }

}

