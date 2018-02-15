package com.shengxiao.bookauthers;

import android.content.Context;
import android.os.FileObserver;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ShengXiao on 2018-02-12.
 */

public class MFileObserver extends FileObserver {
    FileManager fileManager;
    Context ctx;
    public MFileObserver(String path, Context context) {
        super(path);
        ctx=context;
        fileManager=new FileManager(ctx);
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        if(event==8){
            Log.d("Log observer","observer worked----->"+event);
            Log.d("MMMMAIN2",fileManager.readFile(ctx,"t2.txt") );
        }
    }
}
