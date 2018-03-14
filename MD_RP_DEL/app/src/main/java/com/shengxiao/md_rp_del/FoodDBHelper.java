package com.shengxiao.md_rp_del;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by ShengXiao on 2018-03-11.
 */

public class FoodDBHelper {
    private FoodDB foodDB;

    public FoodDBHelper(FoodDB foodDB) {
        this.foodDB = foodDB;
    }

    public void insertFood(FoodInfo...foodInfo){
        new FoodDBInsertManager().execute(foodInfo);
    }

    public void getAllFood(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(FoodInfo food:foodDB.foodDAO().getAllFoodInfo()){
                    Log.d("FOOD",food.toString());
                }
            }
        }).start();
//        new FoodDBGetFoodManager().execute();
    }



    public class FoodDBInsertManager extends AsyncTask<FoodInfo,Void,Void>{
        @Override
        protected Void doInBackground(FoodInfo... foodInfos) {
            for(FoodInfo foodinfo:foodInfos){
                foodDB.foodDAO().insertFood(foodinfo);
            }
            return null;
        }
    }
    public class FoodDBGetFoodManager extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            for(FoodInfo foodInfo:foodDB.foodDAO().getAllFoodInfo()){
                Log.d("Food",foodInfo.toString());
            }
            return null;
        }
    }
}
