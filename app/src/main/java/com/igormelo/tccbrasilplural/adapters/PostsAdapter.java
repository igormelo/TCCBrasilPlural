package com.igormelo.tccbrasilplural.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igormelo.tccbrasilplural.OnItemClickListener;
import com.igormelo.tccbrasilplural.OnItemClickPost;
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.databinding.RowPostsBinding;
import com.igormelo.tccbrasilplural.modelos.Post;

import java.util.ArrayList;


/**
 * Created by root on 03/11/16.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
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
        RowPostsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_posts, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PostsAdapter.ViewHolder holder, final int position) {
        final PostsAdapter.ViewHolder VH = holder;

        final Post myPost = posts.get(position);
        holder.binding.setPosts(myPost);
        holder.binding.layPosts.setOnClickListener(v -> listener.onItemClick(myPost));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowPostsBinding binding;

        public ViewHolder(RowPostsBinding view) {
            super(view.getRoot());
            binding = view;
        }
    }
}
