package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by endamccormack on 16/04/2014.
 */
public class HealthyCampusDataHandler {

    Context ctx;
    HealthyCampusDbHelper hcHelper;
    SQLiteDatabase db;

    public SQLiteDatabase WritableDb(){
        return db;
    }

    public HealthyCampusDataHandler(Context ctx){
        this.ctx = ctx;
        hcHelper = new HealthyCampusDbHelper(ctx);
    }

    public HealthyCampusDataHandler open(){
        db = hcHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        hcHelper.close();
    }
    public long insertData(){
        ContentValues content = new ContentValues();
        return 1;
    }

}
