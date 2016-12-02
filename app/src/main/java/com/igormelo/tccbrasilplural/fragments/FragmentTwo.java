package com.igormelo.tccbrasilplural.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.igormelo.tccbrasilplural.databinding.FragmentTwoBinding;
import com.igormelo.tccbrasilplural.databinding.RecyclerFragTwoBinding;
import com.igormelo.tccbrasilplural.modelos.Comentarios;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class FragmentTwo extends Fragment {
    private CommentsAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    String postid;


    public FragmentTwo() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerFragTwoBinding binding = DataBindingUtil.inflate(inflater, R.layout.recycler_frag_two, container, false);
        View view = binding.getRoot();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_two);
        Service service = Service.retrofit.create(Service.class);

        if(getArguments() != null){
            postid = getArguments().getString("postId");
            binding.setTitle(getArguments().getString("title"));
            binding.setBody(getArguments().getString("body"));
            //binding.getFrag2().recyclerView.setLayoutManager(mLayoutManager);
            //recyclerView.setLayoutManager(mLayoutManager);
            //recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        //// TODO: 10/11/16 criar um metodo que vai chamar o retrofit
        Observable<ArrayList<Comentarios>> call = service.getCommentsByPostId(Integer.valueOf(postid));
        Subscription subscription = call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Comentarios>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<Comentarios> comentarios) {
                        adapter = new CommentsAdapter(comentarios, getActivity(), new OnItemClickComments() {
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
