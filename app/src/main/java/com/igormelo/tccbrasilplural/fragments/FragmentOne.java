package com.igormelo.tccbrasilplural.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.databinding.FragmentOneBinding;


public class FragmentOne extends Fragment{


    public Button button;
    private String url;

    public FragmentOne(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        FragmentOneBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false);
        View view = binding.getRoot();

        //binding.setFrag(this);
        button = (Button) view.findViewById(R.id.button);


        if(getArguments() != null) {
            binding.setName(getArguments().getString("nome"));
            binding.setEmail(getArguments().getString("email"));
            binding.setPhone(getArguments().getString("phone"));
            binding.setSite(getArguments().getString("website"));
            url = binding.getSite();
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button.setOnClickListener(v -> exec());
    }
    private void exec(){

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://" + url));
        getActivity().startActivity(i);
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

