package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.provider.BaseColumns;

/**
 * Created by Enda on 4/7/2014.
 */
public class HealthyCampusDbContract {

    public static final String DATABASE_NAME = "HeathyCampusDb";
    public static final int DATABASE_VERSION = 1;

    public HealthyCampusDbContract(){}

    public static abstract class Recipe implements BaseColumns{
        public static final String TABLE_NAME = "Recipe";
        public static final String COLUMN_NAME_RECIPE_ID = "RecipeId";
        public static final String COLUMN_NAME_TITLE = "Title";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";
        public static final String COLUMN_NAME_METHOD = "Method";
        public static final String COLUMN_NAME_PREP_TIME = "PrepTime";
        public static final String COLUMN_NAME_COOK_TIME = "CookTime";
        public static final String COLUMN_NAME_IMAGE_URL = "ImageURL";
        public static final String COLUMN_NAME_DIFFICULTY_LEVEL = "DifficultyLevel";
    }

    public static abstract class Tag implements BaseColumns{
        public static final String TABLE_NAME = "Tag";
        public static final String COLUMN_NAME_TAG_NAME = "TagName";
    }

    public static abstract class TagRecipes implements BaseColumns{
        public static final String TABLE_NAME = "TagRecipes";
        public static final String COLUMN_NAME_TAG_NAME = "TagName";
        public static final String COLUMN_NAME_RECIPE_ID = "RecipeId";

    }

    public static abstract class Ingredient implements BaseColumns{
        public static final String TABLE_NAME = "Ingredient";
        public static final String COLUMN_NAME_INGREDIENT_ID = "IngredientId";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_AMOUNT_IN_GRAMS = "AmountInGrams";
        public static final String COLUMN_NAME_INGREDIENT_TYPE = "IngredientType";
        public static final String COLUMN_NAME_RECIPE_ID = "RecipeId";
    }
}
