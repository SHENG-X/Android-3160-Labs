package com.example.musfiqrahman.waitlist;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by ShengXiao on 2018-01-17.
 */


public class GuestDBHelper {
    private GuestDB guestDB;

    public GuestDBHelper(GuestDB guestDB, Context context) {
        this.guestDB = guestDB;
    }

    public void asyncInsert(GuestInfo guestInfo) {
        new GuestDBInsertManager().execute(guestInfo);
    }
    public List<GuestInfo> asyncGetAll(final List<GuestInfo> guestIn) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<GuestInfo> guestInfos= (List<GuestInfo>) guestDB.guestDAO().getAllGuestsInfo();
                for(GuestInfo g:guestInfos){
                    guestIn.add(g);
                }
            }
        }).start();
        return guestIn;
    }


    public class GuestDBInsertManager extends AsyncTask<GuestInfo, Void, Void> {

        @Override
        protected Void doInBackground(GuestInfo... guestInfos) {
            for (GuestInfo guestInfo : guestInfos) {
                guestDB.guestDAO().insertGuestInfo(guestInfo);
            }
            return null;
        }

    }

}