package com.Endanger.healthycampusapp.healthycampus.app.database;

import android.media.Image;

/**
 * Created by Enda on 4/7/2014.
 */
public class Recipe {

    private int RecipeId;
    private String Title;
    private String Description;
    private String Method;
    private float PrepTime;
    private float CookTime;
    private int DifficultyLevel;
    private String ImageURL;

    public Recipe(){    }

    public Recipe(int recipeId, String title, String description, String method, float prepTime, float cookTime, int difficultyLevel, String imageURL){
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

    public float getPrepTime() {
        return PrepTime;
    }

    public void setPrepTime(float prepTime) {
        PrepTime = prepTime;
    }

    public float getCookTime() {
        return CookTime;
    }

    public void setCookTime(float cookTime) {
        CookTime = cookTime;
    }

    public int getDifficultyLevel() {
        return DifficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        DifficultyLevel = difficultyLevel;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
