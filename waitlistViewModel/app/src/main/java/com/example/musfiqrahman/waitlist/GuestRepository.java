package com.example.musfiqrahman.waitlist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by ShengXiao on 2018-01-22.
 */

public class GuestRepository {
    private GuestDAO guestDAO;
    private LiveData<List<GuestInfo>> myGuestInfo;

    public GuestRepository(Application app) {
        guestDAO=GuestDB.getDB(app).guestDAO();
        myGuestInfo=guestDAO.getAllGuestsInfo();
    }

    public LiveData<List<GuestInfo>> getMyGuestInfo(){
        return myGuestInfo;
    }

    public void insertGuest(GuestInfo guestInfo){
        new InsertAsyncTask(guestDAO).execute(guestInfo);
    }

    private static class InsertAsyncTask extends AsyncTask<GuestInfo,Void,Void>{
        private GuestDAO guestDAO;

        public InsertAsyncTask(GuestDAO guestDAO) {
            this.guestDAO = guestDAO;
        }

        @Override
        protected Void doInBackground(GuestInfo... guestInfos) {
            guestDAO.insertGuestInfo(guestInfos[0]);
            return null;
        }
    }
}
