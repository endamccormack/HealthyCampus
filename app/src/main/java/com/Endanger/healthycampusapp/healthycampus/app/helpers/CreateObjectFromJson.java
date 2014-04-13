package com.Endanger.healthycampusapp.healthycampus.app.helpers;

import android.util.Log;

import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDbContract;
import com.Endanger.healthycampusapp.healthycampus.app.database.Recipe;

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
               // JSONObject jsonObj = new JSONObject(recipeJson);

                // Getting JSON Array node
                JSONArray recipesJsonArray = new JSONArray(recipeJson);

                // looping through All Contacts
                for (int i = 0; i < recipesJsonArray.length(); i++) {
                    JSONObject rj = recipesJsonArray.getJSONObject(i);
                    Recipe r = new Recipe();

//                    String id = c.getString(TAG_ID);
//                    String name = c.getString(TAG_NAME);
//                    String email = c.getString(TAG_EMAIL);
//                    String address = c.getString(TAG_ADDRESS);
//                    String gender = c.getString(TAG_GENDER);
//
//                    // Phone node is JSON Object
//                    JSONObject phone = c.getJSONObject(TAG_PHONE);
//                    String mobile = phone.getString(TAG_PHONE_MOBILE);
//                    String home = phone.getString(TAG_PHONE_HOME);
//                    String office = phone.getString(TAG_PHONE_OFFICE);

                    // tmp hashmap for single contact
//                    HashMap<String, String> contact = new HashMap<String, String>();
//
//                    // adding each child node to HashMap key => value
//                    contact.put(TAG_ID, id);
//                    contact.put(TAG_NAME, name);
//                    contact.put(TAG_EMAIL, email);
//                    contact.put(TAG_PHONE_MOBILE, mobile);

                    // adding contact to contact list
                    //contactList.add(contact);
                    r.setTitle(rj.getString(HealthyCampusDbContract.Recipe.COLUMN_NAME_TITLE));

                    recipes.add(r);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return recipes;
    }

    public String getRecipeTagObjectsFromJson(String tagsJson){
        return "";
    }

    public String getTagObjectsFromJson(String tagsJson){
        return "";
    }

    public String getIngredientObjectsFromJson(String tagsJson){
        return "";
    }
}
