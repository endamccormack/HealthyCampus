package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enda on 4/7/2014.
 */
public class Ingredient {
    private int IngredientId;
    private String Name;
    private int AmountInGrams;
    private String IngredientType;
    private int RecipeId;

    public  Ingredient(){}

    public Ingredient(int ingredientId, String name, int amountInGrams, String ingredientType, int recipeId)
    {
        IngredientId = ingredientId;
        Name = name;
        AmountInGrams = amountInGrams;
        IngredientType = ingredientType;
        RecipeId = recipeId;
    }

    public int getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(int ingredientId) {
        IngredientId = ingredientId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmountInGrams() {
        return AmountInGrams;
    }

    public void setAmountInGrams(int amountInGrams) {
        AmountInGrams = amountInGrams;
    }

    public String getIngredientType() {
        return IngredientType;
    }

    public void setIngredientType(String ingredientType) {
        IngredientType = ingredientType;
    }

    public int getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(int recipeId) {
        RecipeId = recipeId;
    }

    public long InsertIntoDatabase(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_ID, IngredientId);
        values.put(HealthyCampusDbContract.Ingredient.COLUMN_NAME_NAME, Name);
        values.put(HealthyCampusDbContract.Ingredient.COLUMN_NAME_AMOUNT_IN_GRAMS, AmountInGrams);
        values.put(HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_TYPE, IngredientType);
        values.put(HealthyCampusDbContract.Ingredient.COLUMN_NAME_RECIPE_ID, RecipeId);

        return db.insertOrThrow(HealthyCampusDbContract.Ingredient.TABLE_NAME, null, values);
    }

    public static List<Ingredient> GetAllIngredientsFromDatabase(SQLiteDatabase db){
        Cursor c = db.query(HealthyCampusDbContract.Ingredient.TABLE_NAME,
                new String[]{HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_ID,
                                HealthyCampusDbContract.Ingredient.COLUMN_NAME_NAME,
                                HealthyCampusDbContract.Ingredient.COLUMN_NAME_AMOUNT_IN_GRAMS,
                                HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_TYPE,
                                HealthyCampusDbContract.Ingredient.COLUMN_NAME_RECIPE_ID},
                null,null,null,null,null);

        List<Ingredient> allIngredientsFromDatabase = new ArrayList<Ingredient>();

        if (c.moveToFirst()){
            do {
                Ingredient i = new Ingredient(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getInt(4));
                allIngredientsFromDatabase.add(i);
            } while (c.moveToNext());
            return  allIngredientsFromDatabase;
        }
        else return allIngredientsFromDatabase;
    }

    public static List<Ingredient> GetAllIngredientsForRecipeFromDatabase(SQLiteDatabase db, int recipeId){

        String where = HealthyCampusDbContract.Ingredient.COLUMN_NAME_RECIPE_ID + " = " + recipeId;

        Cursor c = db.query(HealthyCampusDbContract.Ingredient.TABLE_NAME,
                new String[]{HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_ID,
                        HealthyCampusDbContract.Ingredient.COLUMN_NAME_NAME,
                        HealthyCampusDbContract.Ingredient.COLUMN_NAME_AMOUNT_IN_GRAMS,
                        HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_TYPE,
                        HealthyCampusDbContract.Ingredient.COLUMN_NAME_RECIPE_ID},
                where,null,null,null,null);

        List<Ingredient> allIngredientsFromDatabase = new ArrayList<Ingredient>();

        if (c.moveToFirst()){
            do {
                Ingredient i = new Ingredient(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getInt(4));
                allIngredientsFromDatabase.add(i);
            } while (c.moveToNext());
            return  allIngredientsFromDatabase;
        }
        else return allIngredientsFromDatabase;
    }

    public static Ingredient GetIngredientFromDatabase(int ingredientId, int recipeId, SQLiteDatabase db){

        String where = HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_ID + "= " + ingredientId;

        Cursor c = db.query(HealthyCampusDbContract.Ingredient.TABLE_NAME,
                new String[]{HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_ID,
                                HealthyCampusDbContract.Ingredient.COLUMN_NAME_NAME,
                                HealthyCampusDbContract.Ingredient.COLUMN_NAME_AMOUNT_IN_GRAMS,
                                HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_TYPE,
                                HealthyCampusDbContract.Ingredient.COLUMN_NAME_RECIPE_ID}
                ,where ,null,null,null,null);

        Ingredient i = new Ingredient();
        if (c.moveToFirst()){
            i = new Ingredient(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getInt(4));
        }

        return i;
    }
}
