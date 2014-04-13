package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Enda on 4/7/2014.
 */
public class HealthyCampusDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_RECIPE_ENTRIES =
            "CREATE TABLE " + HealthyCampusDbContract.Recipe.TABLE_NAME + " (" +
                    HealthyCampusDbContract.Recipe.COLUMN_NAME_RECIPE_ID + " INTEGER PRIMARY KEY," +
                    HealthyCampusDbContract.Recipe.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    HealthyCampusDbContract.Recipe.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    HealthyCampusDbContract.Recipe.COLUMN_NAME_METHOD + TEXT_TYPE + COMMA_SEP +
                    HealthyCampusDbContract.Recipe.COLUMN_NAME_PREP_TIME + TEXT_TYPE + COMMA_SEP +
                    HealthyCampusDbContract.Recipe.COLUMN_NAME_COOK_TIME + TEXT_TYPE + COMMA_SEP +
                    HealthyCampusDbContract.Recipe.COLUMN_NAME_IMAGE_URL + TEXT_TYPE +
            " )";

    private static final String SQL_CREATE_TAG_ENTRIES =
            "CREATE TABLE " + HealthyCampusDbContract.Tag.TABLE_NAME + " (" +
                    HealthyCampusDbContract.Tag.COLUMN_NAME_TAG_NAME + TEXT_TYPE + " PRIMARY KEY," +
                    " )";

    private static final String SQL_CREATE_INGREDIENT_ENTRIES =
            "CREATE TABLE " + HealthyCampusDbContract.Ingredient.TABLE_NAME + " (" +
                    HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_ID + " INTEGER PRIMARY KEY," +
                    HealthyCampusDbContract.Ingredient.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    HealthyCampusDbContract.Ingredient.COLUMN_NAME_AMOUNT_IN_GRAMS + TEXT_TYPE + COMMA_SEP +
                    HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_TYPE + TEXT_TYPE + COMMA_SEP +
                    HealthyCampusDbContract.Ingredient.COLUMN_NAME_RECIPE_ID + " INTEGER " +
                    " )";

    private static final String SQL_CREATE_TAG_RECIPE_ENTRIES =
            "CREATE TABLE " + HealthyCampusDbContract.TagRecipes.TABLE_NAME + " (" +
                    HealthyCampusDbContract.TagRecipes.COLUMN_NAME_RECIPE_ID + " INTEGER," +
                    HealthyCampusDbContract.TagRecipes.COLUMN_NAME_TAG_NAME + TEXT_TYPE +
                    " )";


    static final String SQL_DELETE_RECIPE_ENTRIES =
            "DROP TABLE IF EXISTS " + HealthyCampusDbContract.Recipe.TABLE_NAME;
    static final String SQL_DELETE_TAG_ENTRIES =
            "DROP TABLE IF EXISTS " + HealthyCampusDbContract.Tag.TABLE_NAME;
    static final String SQL_DELETE_INGREDIENT_ENTRIES =
            "DROP TABLE IF EXISTS " + HealthyCampusDbContract.Ingredient.TABLE_NAME;
    static final String SQL_DELETE_TAG_RECIPE_ENTRIES =
            "DROP TABLE IF EXISTS " + HealthyCampusDbContract.TagRecipes.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HealthCampus.db";

    public HealthyCampusDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RECIPE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_CREATE_TAG_ENTRIES);
        sqLiteDatabase.execSQL(SQL_CREATE_INGREDIENT_ENTRIES);
        sqLiteDatabase.execSQL(SQL_CREATE_TAG_RECIPE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(SQL_DELETE_RECIPE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_TAG_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_INGREDIENT_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_TAG_RECIPE_ENTRIES);

        onCreate(sqLiteDatabase);
    }
}
