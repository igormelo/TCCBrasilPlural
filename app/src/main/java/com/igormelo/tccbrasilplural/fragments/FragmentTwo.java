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
import android.widget.TextView;
import android.widget.Toast;

import com.igormelo.tccbrasilplural.CommentsActivity;
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

import static android.R.attr.data;

/**
 * Created by root on 07/11/16.
 */

public class FragmentTwo extends Fragment {
    private CommentsAdapter adapter;
    private ArrayList<Comentarios> data;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    String postid;
    String titlee;
    String bodyy;

    public FragmentTwo() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_frag_two, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_two);
        Service service = Service.retrofit.create(Service.class);
        if(getArguments() != null){
            postid = getArguments().getString("postId");
            titlee = getArguments().getString("title");
            bodyy = getArguments().getString("body");
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }
        TextView postTitle = (TextView) view.findViewById(R.id.textPostTitle);
        postTitle.setText(titlee);
        TextView postBody = (TextView) view.findViewById(R.id.textPostBody);
        postBody.setText(bodyy);
        //Toast.makeText(getActivity(), "postid" + postid, Toast.LENGTH_SHORT).show();
        //// TODO: 10/11/16 criar um metodo que vai chamar o retrofit
        Call<ArrayList<Comentarios>> call = service.getCommentsByPostId(Integer.valueOf(postid));
        call.enqueue(new Callback<ArrayList<Comentarios>>() {
            @Override
            public void onResponse(Call<ArrayList<Comentarios>> call, Response<ArrayList<Comentarios>> response) {
                if(response.isSuccessful()){
                    data = response.body();
                    adapter = new CommentsAdapter(data, getActivity(), new OnItemClickComments() {
                        @Override
                        public void onItemClick(Comentarios comentarios) {
                            Toast.makeText(getActivity(),"Clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                    mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Comentarios>> call, Throwable t) {

            }
        });

    return view;
    }
    public static FragmentTwo newInstance(String postId,String title, String body) {
        FragmentTwo f2 = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString("postId", postId);
        args.putString("title", title);
        args.putString("body", body);
        f2.setArguments(args);

        return f2;
    }
}
