package com.example.shengx.livedatatesting;

import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shengx.livedatatesting.DB.User;

import java.util.List;

/**
 * Created by SHENG.X on 2018-03-18.
 */

public  class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> myusers;

    public UserAdapter(List<User> myusers) {
        this.myusers = myusers;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.userinfo,parent,false);
        UserHolder uh=new UserHolder(v);
        return uh;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.username.setText(myusers.get(position).getUsername());
        holder.age.setText(myusers.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return myusers.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder{
        TextView username,age;
        public UserHolder(View itemView) {
            super(itemView);
            username=(TextView)itemView.findViewById(R.id.dis_username);
            age=(TextView)itemView.findViewById(R.id.dis_age);
        }
    }
}
