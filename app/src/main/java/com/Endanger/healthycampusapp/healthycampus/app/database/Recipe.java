package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import com.Endanger.healthycampusapp.healthycampus.app.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enda on 4/7/2014.
 */
public class Recipe {

    private int RecipeId;
    private String Title;
    private String Description;
    private String Method;
    private double PrepTime;
    private double CookTime;
    private int DifficultyLevel;
    private String ImageURL;

    String url_images_root = "http://healthycampusportal.azurewebsites.net/Images/uploads/";

    public Recipe(){    }

    public Recipe(int recipeId, String title, String description, String method, double prepTime,
                  double cookTime, int difficultyLevel, String imageURL){
        RecipeId = recipeId;
        Title = title;
        Description = description;
        Method = method;
        PrepTime = prepTime;
        CookTime = cookTime;
        DifficultyLevel = difficultyLevel;
        ImageURL = imageURL;
    }

    public int getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(int recipeId) {
        RecipeId = recipeId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public double getPrepTime() {
        return PrepTime;
    }

    public void setPrepTime(double prepTime) {
        PrepTime = prepTime;
    }

    public double getCookTime() {
        return CookTime;
    }

    public void setCookTime(double cookTime) {
        CookTime = cookTime;
    }

    public int getDifficultyLevel() {
        return DifficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        DifficultyLevel = difficultyLevel;
    }

    public String getImageName() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public long InsertIntoDatabase(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(HealthyCampusDbContract.Recipe.COLUMN_NAME_RECIPE_ID, RecipeId);
        values.put(HealthyCampusDbContract.Recipe.COLUMN_NAME_TITLE, Title);
        values.put(HealthyCampusDbContract.Recipe.COLUMN_NAME_DESCRIPTION, Description);
        values.put(HealthyCampusDbContract.Recipe.COLUMN_NAME_METHOD, Method);
        values.put(HealthyCampusDbContract.Recipe.COLUMN_NAME_PREP_TIME, PrepTime);
        values.put(HealthyCampusDbContract.Recipe.COLUMN_NAME_COOK_TIME, CookTime);
        values.put(HealthyCampusDbContract.Recipe.COLUMN_NAME_DIFFICULTY_LEVEL, DifficultyLevel);
        values.put(HealthyCampusDbContract.Recipe.COLUMN_NAME_IMAGE_URL, ImageURL);


        return db.insertOrThrow(HealthyCampusDbContract.Recipe.TABLE_NAME, null, values);
    }

    public static List<Recipe> GetAllRecipesFromDatabase(SQLiteDatabase db){
        Cursor c = db.query(HealthyCampusDbContract.Recipe.TABLE_NAME,
                new String[]{HealthyCampusDbContract.Recipe.COLUMN_NAME_RECIPE_ID,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_TITLE,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_DESCRIPTION,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_METHOD,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_PREP_TIME,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_COOK_TIME,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_DIFFICULTY_LEVEL,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_IMAGE_URL},
                null,null,null,null,null);

        List<Recipe> allRecipesFromDatabase = new ArrayList<Recipe>();

        if (c.moveToFirst()){
            do {
                Recipe r = new Recipe(c.getInt(0), c.getString(1), c.getString(2),
                                        c.getString(3), c.getFloat(4), c.getFloat(5),
                                        c.getInt(6), c.getString(7));
                allRecipesFromDatabase.add(r);
            } while (c.moveToNext());
            return  allRecipesFromDatabase;
        }
        else return allRecipesFromDatabase;
    }

    public static Recipe GetRecipeFromDatabase(int recipeId, SQLiteDatabase db){

        String where = HealthyCampusDbContract.Recipe.COLUMN_NAME_RECIPE_ID + "= " + recipeId;

        Cursor c = db.query(HealthyCampusDbContract.Recipe.TABLE_NAME,
                new String[]{HealthyCampusDbContract.Recipe.COLUMN_NAME_RECIPE_ID,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_TITLE,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_DESCRIPTION,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_METHOD,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_PREP_TIME,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_COOK_TIME,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_DIFFICULTY_LEVEL,
                                HealthyCampusDbContract.Recipe.COLUMN_NAME_IMAGE_URL}
                ,where ,null,null,null,null);

        Recipe r = new Recipe();
        if (c.moveToFirst()){
            r = new Recipe(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),
                    c.getDouble(4), c.getDouble(5), c.getInt(6), c.getString(7));
        }

        return r;
    }

    public Bitmap GetImage(Context context)
    {
        String imageName = "";

        Bitmap theImage;

        File file;
        //check if there is text in the imageurl string
        if (getImageURL().isEmpty())
        {
            Resources res = context.getResources();
            theImage = BitmapFactory.decodeResource(res,R.drawable.defaultmealimage);
        }
        else {

            file = new File(context.getFilesDir(), "/" + getImageURL());

            //might not be downloaded
            if(!file.exists())
            {
                Resources res = context.getResources();
                theImage = BitmapFactory.decodeResource(res,R.drawable.defaultmealimage);
                //file = new File(context.getFilesDir(), "/defaultmealimage.jpg");
            }else
            {
                theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            }
        }


        return theImage;
    }

    public String getImageURL(){
        return url_images_root + getImageName();
    }
}
