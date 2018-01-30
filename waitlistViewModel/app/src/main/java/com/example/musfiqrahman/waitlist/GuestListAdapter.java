package com.example.musfiqrahman.waitlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by musfiqrahman on 2018-01-09.
 */

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {
    List<GuestInfo> guestList;
    TextView guestNo,guestName;
    LayoutInflater inflater;
    public GuestListAdapter(Context context) {
        inflater=LayoutInflater.from(context);
    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.guest_item_list,parent,false);
        GuestViewHolder vh=new GuestViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        GuestInfo guestInfo=guestList.get(position);
        guestName.setText(guestInfo.getGuestName());
        guestNo.setText(String.valueOf(guestInfo.getPartySize()));
    }

    @Override
    public int getItemCount() {
        if(guestList != null)
            return guestList.size();
        return 0;
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder{

        public GuestViewHolder(View itemView) {
            super(itemView);
            guestNo=(TextView)itemView.findViewById(R.id.party_size_text_view);
            guestName=(TextView)itemView.findViewById(R.id.name_text_view);
        }
    }
    public void addGuest(List<GuestInfo> guestInfoList){
        guestList=guestInfoList;
        notifyDataSetChanged();
    }

}
