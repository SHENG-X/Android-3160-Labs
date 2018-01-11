package com.example.musfiqrahman.waitlist;

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
    private List<GuestInfo> guestList;
    TextView guestName, partySize;
    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.guest_item_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        GuestViewHolder vh = new GuestViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        GuestInfo guestInfo=guestList.get(position);
        guestName.setText(guestInfo.getGuestName());
        partySize.setText(String.valueOf(guestInfo.getPartySize()));
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public GuestListAdapter(List<GuestInfo> guestList) {
        this.guestList = guestList;
    }

    class GuestViewHolder extends RecyclerView.ViewHolder {
        public GuestViewHolder(View itemView) {
            super(itemView);
            guestName=(TextView)itemView.findViewById(R.id.name_text_view);
            partySize=(TextView)itemView.findViewById(R.id.party_size_text_view);
        }
        public void addGuest(int position, GuestInfo myguest){
            guestList.add(position,myguest);
            notifyItemInserted(position);
        }
    }

}
