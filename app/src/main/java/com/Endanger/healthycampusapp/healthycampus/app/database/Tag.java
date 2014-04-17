package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enda on 4/7/2014.
 */
public class Tag {

    private String TagName;

    public Tag(){}
    public Tag(String tagName){
        TagName = tagName;
    }

    public void setTagName(String tagName){
        TagName = tagName;
    }

    public String getTagName(){
        return TagName;
    }

    public long InsertIntoDatabase(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(HealthyCampusDbContract.Tag.COLUMN_NAME_TAG_NAME, TagName);

        return db.insertOrThrow(HealthyCampusDbContract.Tag.TABLE_NAME, null, values);
    }

    public static List<Tag> GetAllTagsFromDatabase(SQLiteDatabase db){
        Cursor c = db.query(HealthyCampusDbContract.Tag.TABLE_NAME,
                new String[]{HealthyCampusDbContract.Tag.COLUMN_NAME_TAG_NAME},
                null,null,null,null,null);

        List<Tag> allTagsFromDatabase = new ArrayList<Tag>();

        if (c.moveToFirst()){
            do {
                Tag t = new Tag(c.getString(0));
                allTagsFromDatabase.add(t);
            } while (c.moveToNext());
            return  allTagsFromDatabase;
        }
        else return allTagsFromDatabase;
    }

    public static Tag GetTagFromDatabase(String tagName,SQLiteDatabase db){
        String where = HealthyCampusDbContract.Tag.COLUMN_NAME_TAG_NAME + "= '" + tagName + "'";

        Cursor c = db.query(HealthyCampusDbContract.Tag.TABLE_NAME,
                            new String[]{HealthyCampusDbContract.Tag.COLUMN_NAME_TAG_NAME}
                            ,where.toString() ,null,null,null,null);

        if (c.moveToFirst()){
            Tag t = new Tag(c.getString(0));
            return t;
        }
        return new Tag();
    }
}
