package com.Endanger.healthycampusapp.healthycampus.app.helpers;

import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDbContract;
import com.Endanger.healthycampusapp.healthycampus.app.database.Ingredient;
import com.Endanger.healthycampusapp.healthycampus.app.database.Recipe;
import com.Endanger.healthycampusapp.healthycampus.app.database.Tag;
import com.Endanger.healthycampusapp.healthycampus.app.database.TagRecipes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Enda on 12/4/2014.
 */
public class CreateObjectFromJson {
    public CreateObjectFromJson(){   }

    public List<Recipe> getRecipeObjectsFromJson(String recipeJson){

        List<Recipe> recipes = new ArrayList<Recipe>();

        if (recipeJson != null) {
            try {
                JSONArray recipesJsonArray = new JSONArray(recipeJson);

                for (int i = 0; i < recipesJsonArray.length(); i++) {
                    JSONObject rj = recipesJsonArray.getJSONObject(i);
                    Recipe r = new Recipe();

                    r.setRecipeId(rj.getInt(HealthyCampusDbContract.Recipe.COLUMN_NAME_RECIPE_ID));
                    r.setTitle(rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_TITLE) != "null" ? rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_TITLE) : "Unknown");
                    r.setDescription(rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_DESCRIPTION) != "null" ? rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_DESCRIPTION) : "Unknown");
                    r.setMethod(rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_METHOD) != "null" ? rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_METHOD) : "Unknown");
                    r.setPrepTime(rj.getDouble(HealthyCampusDbContract.Recipe.COLUMN_NAME_PREP_TIME));
                    r.setCookTime(rj.getDouble(HealthyCampusDbContract.Recipe.COLUMN_NAME_COOK_TIME));
                    r.setDifficultyLevel(rj.getInt(HealthyCampusDbContract.Recipe.COLUMN_NAME_DIFFICULTY_LEVEL));
                    r.setImageURL(rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_IMAGE_URL) != "null" ? rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_IMAGE_URL)  : "");

                    recipes.add(r);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }

        return recipes;
    }

    public List<TagRecipes> getRecipeTagObjectsFromJson(String tagsRecipeJson){
        List<TagRecipes> tagRecipeses = new ArrayList<TagRecipes>();

        if (tagsRecipeJson != null) {
            try {
                JSONArray recipestagJsonArray = new JSONArray(tagsRecipeJson);

                for (int i = 0; i < recipestagJsonArray.length(); i++) {
                    JSONObject rj = recipestagJsonArray.getJSONObject(i);
                    TagRecipes tr = new TagRecipes();

                    tr.setRecipeId(rj.getInt(HealthyCampusDbContract.TagRecipes.COLUMN_NAME_RECIPE_ID));
                    tr.setTagName(rj.getString(HealthyCampusDbContract.TagRecipes.COLUMN_NAME_TAG_NAME));

                    tagRecipeses.add(tr);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }

        return tagRecipeses;
    }

    public List<Tag> getTagObjectsFromJson(String tagsJson){
        List<Tag> tags = new ArrayList<Tag>();

        if (tagsJson != null) {
            try {
                JSONArray tagsJsonArray = new JSONArray(tagsJson);

                for (int i = 0; i < tagsJsonArray.length(); i++) {
                    JSONObject rj = tagsJsonArray.getJSONObject(i);
                    Tag t = new Tag();

                    t.setTagName(rj.getString(HealthyCampusDbContract.Tag.COLUMN_NAME_TAG_NAME));

                    tags.add(t);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return tags;
    }

    public List<Ingredient> getIngredientObjectsFromJson(String ingredientsJson){
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        if (ingredientsJson != null) {
            try {
                JSONArray ingredientsJsonArray = new JSONArray(ingredientsJson);

                for (int j = 0; j < ingredientsJsonArray.length(); j++) {
                    JSONObject ij = ingredientsJsonArray.getJSONObject(j);
                    Ingredient i = new Ingredient();

                    i.setIngredientId(ij.getInt(HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_ID));

                    i.setName(ij.getString(HealthyCampusDbContract.Ingredient.COLUMN_NAME_NAME) != "null" ?
                            ij.getString(HealthyCampusDbContract.Ingredient.COLUMN_NAME_NAME) : "Unknown");

                    i.setIngredientType(ij.getString(HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_TYPE) != "null" ?
                            ij.getString(HealthyCampusDbContract.Ingredient.COLUMN_NAME_INGREDIENT_TYPE) : "Unknown");

                    i.setAmountInGrams(ij.getInt(HealthyCampusDbContract.Ingredient.COLUMN_NAME_AMOUNT_IN_GRAMS));
                    i.setRecipeId(ij.getInt(HealthyCampusDbContract.Ingredient.COLUMN_NAME_RECIPE_ID));


                    ingredients.add(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }

        return ingredients;
    }
}
