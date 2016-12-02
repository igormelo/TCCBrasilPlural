package com.igormelo.tccbrasilplural.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.databinding.FragmentTwoBinding;
import com.igormelo.tccbrasilplural.databinding.RecyclerFragTwoBinding;
import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Post;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;

/**
 * Created by root on 08/11/16.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private ArrayList<Comentarios> info;
    private ArrayList<Post> post;
    private Context context;

    public InfoAdapter(ArrayList<Comentarios> info, ArrayList<Post> post, Context context){
        this.info = info;
        this.post = post;
        this.context = context;
    }

    @Override
    public InfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentTwoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.fragment_two, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(InfoAdapter.ViewHolder holder, int position) {

        final InfoAdapter.ViewHolder VH = holder;
        final Comentarios myInfoComments = info.get(position);
        final Post myInfoPost = post.get(position);
        holder.binding.setTitle(myInfoPost.getUserId());
        holder.binding.setTitle(myInfoPost.getTitle());
        holder.binding.setBody(myInfoComments.getBody());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
;
        private FragmentTwoBinding binding;
        public ViewHolder(FragmentTwoBinding view) {
            super(view.getRoot());
            binding = view;

        }
    }
}
