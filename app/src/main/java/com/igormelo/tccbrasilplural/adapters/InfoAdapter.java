package com.igormelo.tccbrasilplural.adapters;

import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igormelo.tccbrasilplural.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_two, parent,false);
        return new InfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoAdapter.ViewHolder holder, int position) {
        final InfoAdapter.ViewHolder VH = holder;
        final Comentarios myInfoComments = info.get(position);
        final Post myInfoPost = post.get(position);
        VH.title.setText(myInfoPost.getTitle());
        VH.body.setText(myInfoComments.getBody());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,body;
        private LinearLayout fragment_two;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titlee);
            body = (TextView) view.findViewById(R.id.bodyy);

            fragment_two = (LinearLayout) view.findViewById(R.id.fragment_two);

        }
    }
}
