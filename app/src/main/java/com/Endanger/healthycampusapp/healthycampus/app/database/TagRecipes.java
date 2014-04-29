package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enda on 4/7/2014.
 */
public class TagRecipes {
    private String TagName;
    private int RecipeId;

    public TagRecipes(){}
    public TagRecipes(String tagName, int recipeId){
        TagName = tagName;
        RecipeId = recipeId;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public int getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(int recipeId) {
        RecipeId = recipeId;
    }

    public long InsertIntoDatabase(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(HealthyCampusDbContract.TagRecipes.COLUMN_NAME_TAG_NAME, TagName);
        values.put(HealthyCampusDbContract.TagRecipes.COLUMN_NAME_RECIPE_ID, RecipeId);

        return db.insertOrThrow(HealthyCampusDbContract.TagRecipes.TABLE_NAME, null, values);
    }

    public static List<TagRecipes> GetAllTagRecipesFromDatabase(SQLiteDatabase db){
        Cursor c = db.query(HealthyCampusDbContract.TagRecipes.TABLE_NAME,
                new String[]{HealthyCampusDbContract.TagRecipes.COLUMN_NAME_TAG_NAME,
                            HealthyCampusDbContract.TagRecipes.COLUMN_NAME_RECIPE_ID},
                null,null,null,null,null);

        List<TagRecipes> allTagRecipesFromDatabase = new ArrayList<TagRecipes>();

        if (c.moveToFirst()){
            do {
                TagRecipes t = new TagRecipes(c.getString(0), c.getInt(1));
                allTagRecipesFromDatabase.add(t);
            } while (c.moveToNext());
            return  allTagRecipesFromDatabase;
        }
        else return allTagRecipesFromDatabase;
    }

    public static TagRecipes GetTagRecipeFromDatabase(String tagName, int recipeId, SQLiteDatabase db){
        String where = HealthyCampusDbContract.TagRecipes.COLUMN_NAME_TAG_NAME + " = '" + tagName + "' AND " +
                        HealthyCampusDbContract.TagRecipes.COLUMN_NAME_RECIPE_ID +" = " + recipeId;

        Cursor c = db.query(HealthyCampusDbContract.TagRecipes.TABLE_NAME,
                new String[]{HealthyCampusDbContract.TagRecipes.COLUMN_NAME_TAG_NAME,
                            HealthyCampusDbContract.TagRecipes.COLUMN_NAME_RECIPE_ID}
                ,where ,null,null,null,null);

        TagRecipes t = new TagRecipes();
        if (c.moveToFirst()){
            t = new TagRecipes(c.getString(0), c.getInt(1));
        }

        return t;
    }
}
