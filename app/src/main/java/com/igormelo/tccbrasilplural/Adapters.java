package com.igormelo.tccbrasilplural;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapters extends RecyclerView.Adapter<Adapters.ViewHolder>{
    private ArrayList<Users> users;
    public Adapters(ArrayList<Users> users){
        this.users = users;
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
        VH.tv_id.setText(users.get(position).getId());
        VH.tv_name.setText(users.get(position).getName());
        VH.tv_email.setText(users.get(position).getEmail());
        VH.tv_street.setText(users.get(position).getAddress().getStreet());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id,tv_name,tv_email,tv_street;
        public ViewHolder(View view){
            super(view);
            tv_id = (TextView)view.findViewById(R.id.tv_id);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_email = (TextView)view.findViewById(R.id.tv_email);
            tv_street =(TextView)view.findViewById(R.id.tv_street);

        }
    }
}
