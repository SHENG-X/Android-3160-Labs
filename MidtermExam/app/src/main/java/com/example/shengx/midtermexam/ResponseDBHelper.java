package com.example.shengx.midtermexam;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by SHENG.X on 2018-03-19.
 */

public class ResponseDBHelper {

    private ResponseDB responseDB;

    public ResponseDBHelper(ResponseDB responseDB,Context context){
        this.responseDB=responseDB;
    }

    public void asynInsertResponse(Response response){
        new ResponseInsertManager().execute(response);
    }

    public AsyncTask<Integer, Void, Boolean> asynCheckNumberInDB(int num){
        return new ResponseNumChecker().execute(num);
    }

    public class ResponseInsertManager extends AsyncTask<Response,Void,Void>{

        @Override
        protected Void doInBackground(Response... responses) {
            for(Response respons:responses){
                responseDB.responseDAO().insertNumber(respons);
            }
            return null;
        }
    }
    public class ResponseNumChecker extends AsyncTask<Integer,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Integer... integers) {
            return responseDB.responseDAO().getSpecificNumber(integers[0]);
        }

    }
}
