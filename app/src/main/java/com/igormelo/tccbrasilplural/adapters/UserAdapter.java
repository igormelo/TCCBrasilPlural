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
import com.igormelo.tccbrasilplural.R;
import com.igormelo.tccbrasilplural.databinding.RowUsersBinding;
import com.igormelo.tccbrasilplural.modelos.Users;

import java.util.ArrayList;

/**
 * Created by root on 03/11/16.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<Users> users;
    private Context context;
    private OnItemClickListener listener;

public UserAdapter(ArrayList<Users> users, Context context, OnItemClickListener listener){
    this.users = users;
    this.context = context;
    this.listener = listener;
}



    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowUsersBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_users, parent, false);
        //View view = LayoutInflater.from(parent.getContext())
        //          .inflate(R.layout.row_users, parent, false);
        //UserAdapter.ViewHolder userAdapterViewHolder = new UserAdapter.ViewHolder(view);
        //return userAdapterViewHolder;
        //return new UserAdapter.ViewHolder(view);
        return new ViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, final int position) {
        final UserAdapter.ViewHolder VH = holder;
        final Users myUser = users.get(position);
        holder.binding.setUsers(myUser);
        holder.binding.layPrincipal.setOnClickListener(v-> listener.onItemClick(myUser));
        //VH.tv_id.setText("ID: " + myUser.getId());
        //VH.tv_name.setText("name: " + myUser.getName());
        //VH.tv_email.setText("email: "+myUser.getEmail());
        //VH.tv_street.setText("street" +myUser.getAddress().getStreet());
        //VH.layPrincipal.setOnClickListener(v -> listener.onItemClick(myUser));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowUsersBinding binding;


        public ViewHolder(RowUsersBinding view) {
            super(view.getRoot());
            binding = view;

            /*super(view);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_email = (TextView) view.findViewById(R.id.tv_email);
            tv_street = (TextView) view.findViewById(R.id.tv_street);

            layPrincipal = (LinearLayout) view.findViewById(R.id.layPrincipal);*/

        }
    }
}
