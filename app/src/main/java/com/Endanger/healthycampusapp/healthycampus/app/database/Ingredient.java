package com.Endanger.healthycampusapp.healthycampus.app.database;

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
}
