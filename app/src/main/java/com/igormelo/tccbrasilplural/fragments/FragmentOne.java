package com.igormelo.tccbrasilplural.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.igormelo.tccbrasilplural.R;

import java.net.URL;

public class FragmentOne extends Fragment{

    String nome;
    String emaill;
    String phonee;
    String websitee;
    Button button;
    private String url;

    public FragmentOne(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_one, container, false);
        button = (Button) view.findViewById(R.id.button);


        if(getArguments() != null) {
            nome = getArguments().getString("nome");
            emaill = getArguments().getString("email");
            phonee = getArguments().getString("phone");
            websitee = getArguments().getString("website");
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView email = (TextView) view.findViewById(R.id.email);
        TextView phone = (TextView) view.findViewById(R.id.telefone);
        TextView website = (TextView) view.findViewById(R.id.site);
        name.setText(nome);
        email.setText(emaill);
        phone.setText(phonee);
        website.setText(websitee);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String url = websitee;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://" + url));
                    getActivity().startActivity(i);
            }
        });
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
    public void verificaUrl(String url){
        this.url = websitee;
    }

}

