package com.igormelo.tccbrasilplural.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igormelo.tccbrasilplural.OnItemClickListener;
import com.igormelo.tccbrasilplural.OnItemClickPost;
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.modelos.Post;

import java.util.ArrayList;


/**
 * Created by root on 03/11/16.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private ArrayList<Post> posts;
    private Context context;
    private OnItemClickPost listener;


    public PostsAdapter(ArrayList<Post> posts, Context context, OnItemClickPost listener) {
        this.posts = posts;
        this.context = context;
        this.listener = listener;
    }


    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_posts, parent, false);
        return new PostsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostsAdapter.ViewHolder holder, final int position) {
        final PostsAdapter.ViewHolder VH = holder;

        final Post myPost = posts.get(position);
        VH.userid.setText("userId: "+myPost.getUserId());
        VH.id.setText("ID: "+myPost.getId());
        VH.title.setText(myPost.getTitle());
        VH.body.setText(myPost.getBody());


        VH.layPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(myPost);

            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userid,id,title,body;
        private LinearLayout layPosts;

        public ViewHolder(View view) {
            super(view);
            userid = (TextView) view.findViewById(R.id.userid);
            id = (TextView) view.findViewById(R.id.id);
            title = (TextView) view.findViewById(R.id.title);
            body = (TextView)view.findViewById(R.id.body);


            layPosts = (LinearLayout) view.findViewById(R.id.layPosts);

        }
    }
}
