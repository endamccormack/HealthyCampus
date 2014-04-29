package com.Endanger.healthycampusapp.healthycampus.app;

/**
 * Created by Enda on 02/04/2014.
 */
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDataHandler;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDbContract;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDbHelper;
import com.Endanger.healthycampusapp.healthycampus.app.database.Ingredient;
import com.Endanger.healthycampusapp.healthycampus.app.database.Recipe;
import com.Endanger.healthycampusapp.healthycampus.app.database.Tag;
import com.Endanger.healthycampusapp.healthycampus.app.database.TagRecipes;
import com.Endanger.healthycampusapp.healthycampus.app.helpers.CreateObjectFromJson;
import com.Endanger.healthycampusapp.healthycampus.app.helpers.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3000;

    String url_Recipes = "http://healthycampusportal.azurewebsites.net/Recipe/recipejson";
    String url_Recipe_Tag = "http://healthycampusportal.azurewebsites.net/Recipe/RecipeTagJson";
    String url_Tags = "http://healthycampusportal.azurewebsites.net/Recipe/TagJson";
    String url_Ingredients = "http://healthycampusportal.azurewebsites.net/Recipe/IngredientJson";

    String url_images_root = "http://healthycampusportal.azurewebsites.net/Images/uploads/";

    ArrayList<HashMap<String, String>> arraylist;
    JSONObject jsonobject;
    JSONArray jsonarray;

    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);

        if(!doesDatabaseExist() && !isNetworkAvailable())
        {
            new AlertDialog.Builder(this)
                    .setTitle("No data")
                    .setMessage("It appears that no data has been previously loaded and that there is no internet connection to download recipes, please connect to the internet and try again")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
        else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {

                }
            }, 2000);


            if (isNetworkAvailable()) {
                new DownloadJSON().execute();
            } else {
                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private boolean doesDatabaseExist() {
        File dbFile = getApplicationContext().getDatabasePath(HealthyCampusDbContract.DATABASE_NAME);
        return dbFile.exists();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog = new ProgressDialog(Splash.this);
        InputStream inputStream = null;
        String result = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            mProgressDialog = new ProgressDialog(Splash.this);
//
//            mProgressDialog.setTitle("Health Campus");
//
//            mProgressDialog.setMessage("Welcome to Healthy Campus \n Loading new data...");
//            mProgressDialog.setIndeterminate(false);
//
//            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ServiceHandler sh = new ServiceHandler();

            HealthyCampusDataHandler dHandler = new HealthyCampusDataHandler(getBaseContext());
            HealthyCampusDbHelper dHelper = new HealthyCampusDbHelper(getBaseContext());

            dHandler.open();
            dHelper.ResetDatabaseDefault(dHandler.WritableDb());
            dHandler.close();

           // getApplicationContext().deleteDatabase(HealthyCampusDbContract.DATABASE_NAME);

            // Making a request to url and getting response
            String recipeJson = sh.makeServiceCall(url_Recipes , ServiceHandler.GET);
            List<Recipe> r = new CreateObjectFromJson().getRecipeObjectsFromJson(recipeJson);

            dHandler.open();
            List<Recipe> newR = Recipe.GetAllRecipesFromDatabase(dHandler.WritableDb());
            dHandler.close();

            for(int i = 0; i < r.size(); i++)
            {
                dHandler.open();
                r.get(i).InsertIntoDatabase(dHandler.WritableDb());
                dHandler.close();
//                if(r.get(i).getImageURL() != "")
//                    new ImageDownloader(url_images_root + r.get(i).getImageURL()).execute();
            }
            new ImageDownloader(r).execute();

            String recipeTagJson = sh.makeServiceCall(url_Recipe_Tag , ServiceHandler.GET);
            List<TagRecipes> rt = new CreateObjectFromJson().getRecipeTagObjectsFromJson(recipeTagJson);

            for(int i = 0; i < rt.size(); i++)
            {
                dHandler.open();
                rt.get(i).InsertIntoDatabase(dHandler.WritableDb());
                dHandler.close();
            }

            String tagJson = sh.makeServiceCall(url_Tags , ServiceHandler.GET);
            List<Tag> t = new CreateObjectFromJson().getTagObjectsFromJson(tagJson);

            for(int i = 0; i < t.size(); i++)
            {
                dHandler.open();
                t.get(i).InsertIntoDatabase(dHandler.WritableDb());
                dHandler.close();
            }

            String ingredientJson = sh.makeServiceCall(url_Ingredients , ServiceHandler.GET);
            List<Ingredient> ing = new CreateObjectFromJson().getIngredientObjectsFromJson(ingredientJson);

            for(int i = 0; i < ing.size(); i++)
            {
                dHandler.open();
                ing.get(i).InsertIntoDatabase(dHandler.WritableDb());
                dHandler.close();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void args) {

            //new ImageDownloader("http://photojournal.jpl.nasa.gov/jpeg/PIA17555.jpg").execute();

            Intent mainIntent = new Intent(Splash.this,MainActivity.class);
            Splash.this.startActivity(mainIntent);
            Splash.this.finish();
            //mProgressDialog.setMessage(result);


//            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getApplicationContext());

//            dlgAlert.setMessage(result.toString());

//            dlgAlert.setTitle("App Title");
//            dlgAlert.setPositiveButton("OK", null);
//            dlgAlert.setCancelable(true);
//            dlgAlert.create().show();

            // Locate the listview in listview_main.xml
            //listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            //adapter = new ListViewAdapter(MainActivity.this, arraylist);
            // Set the adapter to the ListView
            //listview.setAdapter(adapter);
            // Close the progressdialog
            //mProgressDialog.dismiss();
        }
    }

    public class ImageDownloader extends AsyncTask<Void, Void, Void> {
        private String Url;
        private String FILENAME;
        private List<Recipe> Recipes;

//        public ImageDownloader(String url){
//            Url = url; //FILENAME = filename;
//        }

        public ImageDownloader(List<Recipe> recipes){
            Recipes = recipes;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            for (int i = 0; i< Recipes.size(); i++) {
                if (Recipes.get(i).getImageURL() != "") {
                    Bitmap myBitmap = getBitmapFromURL(url_images_root + Recipes.get(i).getImageURL());

                    try {
                        FileOutputStream fos = openFileOutput(Recipes.get(i).getImageURL(), Context.MODE_PRIVATE);
                        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

        }

        public Bitmap getBitmapFromURL(String link) {
            Bitmap myBitmap;
            try {
                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("getBmpFromUrl error: ", e.getMessage().toString());
                return null;
            }

            Bitmap resizedbitmap = Bitmap.createScaledBitmap(myBitmap, 800, 600, true);

            return resizedbitmap;
        }
    }
}
