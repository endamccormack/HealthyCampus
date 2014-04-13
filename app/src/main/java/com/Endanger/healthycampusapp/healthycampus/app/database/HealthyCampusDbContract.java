package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.provider.BaseColumns;

/**
 * Created by Enda on 4/7/2014.
 */
public class HealthyCampusDbContract {

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
    }

    public static abstract class Tag implements BaseColumns{
        public static final String TABLE_NAME = "Tag";
        public static final String COLUMN_NAME_TAG_NAME = "TagName";
    }

    public static abstract class TagRecipes implements BaseColumns{
        public static final String TABLE_NAME = "TagRecipes";
        public static final String COLUMN_NAME_TAG_NAME = "Tag_TagName";
        public static final String COLUMN_NAME_RECIPE_ID = "Recipe_RecipeId";

    }

    public static abstract class Ingredient implements BaseColumns{
        public static final String TABLE_NAME = "Ingredient";
        public static final String COLUMN_NAME_INGREDIENT_ID = "entryid";
        public static final String COLUMN_NAME_NAME = "title";
        public static final String COLUMN_NAME_AMOUNT_IN_GRAMS = "subtitle";
        public static final String COLUMN_NAME_INGREDIENT_TYPE = "entryid";
        public static final String COLUMN_NAME_RECIPE_ID = "title";
    }
}
