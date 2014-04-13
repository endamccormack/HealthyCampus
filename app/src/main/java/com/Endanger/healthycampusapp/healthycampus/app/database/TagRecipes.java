package com.Endanger.healthycampusapp.healthycampus.app.database;

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
}
