package com.example.musfiqrahman.waitlist;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by musfiqrahman on 2018-01-09.
 */

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {
    private Cursor mCursor;

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("adp","cp1");
        return new GuestViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        if(mCursor.moveToPosition(position)){
            holder.guestName.setText(mCursor.getString(mCursor.getColumnIndex(GuestContract.GuestEntity.COLUMN_GUEST_NAME)));
            holder.guestNo.setText(mCursor.getString(mCursor.getColumnIndex(GuestContract.GuestEntity.COLUMN_PARTY_SIZE)));
        }
    }

    @Override
    public int getItemCount() {
        return mCursor==null?0:mCursor.getCount();
    }

    public void setGuest(Cursor cursor){
        mCursor=cursor;
        Log.d("adp","cp3");
        notifyDataSetChanged();
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder{
        TextView guestName, guestNo;
        public GuestViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.guest_item_list, parent, false));
            guestNo=(TextView)itemView.findViewById(R.id.party_size_text_view);
            guestName=(TextView)itemView.findViewById(R.id.name_text_view);
        }
    }


}
