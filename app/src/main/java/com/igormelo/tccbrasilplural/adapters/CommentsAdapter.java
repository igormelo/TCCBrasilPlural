package com.igormelo.tccbrasilplural.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igormelo.tccbrasilplural.CommentsActivity;
import com.igormelo.tccbrasilplural.OnItemClickComments;
import com.igormelo.tccbrasilplural.OnItemClickListener;
import com.igormelo.tccbrasilplural.OnItemClickPost;
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.fragments.FragmentOne;
import com.igormelo.tccbrasilplural.modelos.Comentarios;
import com.igormelo.tccbrasilplural.modelos.Post;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_comments, parent, false);
        return new CommentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, final int position) {
        final CommentsAdapter.ViewHolder VH = holder;

        final Comentarios myComments = comments.get(position);
        VH.postid.setText(myComments.getPostId());
        VH.id.setText(myComments.getId());
        VH.name.setText(myComments.getName());
        VH.email.setText(myComments.getEmail());
        VH.body.setText(myComments.getBody());


        VH.layComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(myComments);

            }
        });

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView postid, id, name, email, body;
        private LinearLayout layComments;

        public ViewHolder(View view) {
            super(view);
            postid = (TextView) view.findViewById(R.id.postid);
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            body = (TextView) view.findViewById(R.id.body);


            layComments = (LinearLayout) view.findViewById(R.id.layComments);

        }
    }
}