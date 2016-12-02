package com.igormelo.tccbrasilplural.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.igormelo.tccbrasilplural.OnItemClickComments;
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.databinding.RowCommentsBinding;
import com.igormelo.tccbrasilplural.modelos.Comentarios;
import java.util.ArrayList;

/**
 * Created by root on 03/11/16.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private ArrayList<Comentarios> comments;
    private Context context;
    private OnItemClickComments listener;


    public CommentsAdapter(ArrayList<Comentarios> comments, Context context, OnItemClickComments listener) {
        this.comments = comments;
        this.context = context;
        this.listener = listener;
    }


    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowCommentsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_comments, parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, final int position) {
        final CommentsAdapter.ViewHolder VH = holder;

        final Comentarios myComments = comments.get(position);
        holder.binding.setComents(myComments);
        holder.binding.layComments.setOnClickListener(v -> listener.onItemClick(myComments));

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowCommentsBinding binding;


        public ViewHolder(RowCommentsBinding view) {
            super(view.getRoot());
            binding = view;

        }
    }
}