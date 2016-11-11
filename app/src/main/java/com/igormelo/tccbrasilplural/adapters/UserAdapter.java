package com.igormelo.tccbrasilplural.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.igormelo.tccbrasilplural.OnItemClickListener;
import com.igormelo.tccbrasilplural.R;
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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_users, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, final int position) {
        final UserAdapter.ViewHolder VH = holder;

        final Users myUser = users.get(position);

        VH.tv_id.setText("ID: " + myUser.getId());
        VH.tv_name.setText("name: " + myUser.getName());
        VH.tv_email.setText("email: "+myUser.getEmail());
        VH.tv_street.setText("street" +myUser.getAddress().getStreet());


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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id, tv_name, tv_email, tv_street;
        private LinearLayout layPrincipal;

        public ViewHolder(View view) {
            super(view);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_email = (TextView) view.findViewById(R.id.tv_email);
            tv_street = (TextView) view.findViewById(R.id.tv_street);

            layPrincipal = (LinearLayout) view.findViewById(R.id.layPrincipal);

        }
    }
}
