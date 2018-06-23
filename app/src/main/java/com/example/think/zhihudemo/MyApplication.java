package com.example.think.zhihudemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.think.zhihudemo.green.dao.DaoMaster;
import com.example.think.zhihudemo.green.dao.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by think on 2017/12/23.
 */

public class MyApplication extends Application {

    private DaoSession session;
    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initGreenDao();
        initFresco();
    }

    private void initFresco() {
        Fresco.initialize(this);
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "msg.db");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster master = new DaoMaster(writableDatabase);
        session = master.newSession();
    }

    public DaoSession getSession() {
        return session;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
}
