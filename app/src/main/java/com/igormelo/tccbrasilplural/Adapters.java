package com.igormelo.tccbrasilplural;


import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapters extends RecyclerView.Adapter<Adapters.ViewHolder>{
    private ArrayList<Users> users;
    private Context context;
    private OnUserItemClickListener listener;

    public Adapters(ArrayList<Users> users, Context context, OnUserItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapters.ViewHolder holder, final int position) {
        final Adapters.ViewHolder VH = holder;

        final Users myUser = users.get(position);

        VH.tv_id.setText(users.get(position).getId());
        VH.tv_name.setText(users.get(position).getName());
        VH.tv_email.setText(users.get(position).getEmail());
        VH.tv_street.setText(users.get(position).getAddress().getStreet());

        VH.layPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(myUser);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id,tv_name,tv_email,tv_street;
        private LinearLayout layPrincipal;
        public ViewHolder(View view){
            super(view);
            tv_id = (TextView)view.findViewById(R.id.tv_id);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_email = (TextView)view.findViewById(R.id.tv_email);
            tv_street =(TextView)view.findViewById(R.id.tv_street);

            layPrincipal =(LinearLayout)view.findViewById(R.id.layPrincipal);

        }
    }
}
