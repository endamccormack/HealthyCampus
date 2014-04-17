
package com.Endanger.healthycampusapp.healthycampus.app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDataHandler;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDbContract;
import com.Endanger.healthycampusapp.healthycampus.app.database.Ingredient;
import com.Endanger.healthycampusapp.healthycampus.app.database.Recipe;
import com.Endanger.healthycampusapp.healthycampus.app.database.Tag;
import com.Endanger.healthycampusapp.healthycampus.app.database.TagRecipes;
import com.Endanger.healthycampusapp.healthycampus.app.helpers.CreateObjectFromJson;
import com.Endanger.healthycampusapp.healthycampus.app.helpers.ServiceHandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    CustomDrawerAdapter adapter;
    ArrayList<HashMap<String, String>> arraylist;
    JSONObject jsonobject;
    JSONArray jsonarray;

    ProgressDialog mProgressDialog;


    List<DrawerItem> dataList;
    Fragment fragment;
    String url_Recipes = "http://healthycampusportal.azurewebsites.net/Recipe/recipejson";
    String url_Recipe_Tag = "http://healthycampusportal.azurewebsites.net/Recipe/RecipeTagJson";
    String url_Tags = "http://healthycampusportal.azurewebsites.net/Recipe/TagJson";
    String url_Ingredients = "http://healthycampusportal.azurewebsites.net/Recipe/IngredientJson";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Initializing
        fragment = null;
        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        // Add Drawer Item to dataList
        dataList.add(new DrawerItem("Home", R.drawable.home));
        dataList.add(new DrawerItem("Breakfast", R.drawable.breakfast));
        dataList.add(new DrawerItem("Lunch", R.drawable.lunch));
        dataList.add(new DrawerItem("Dinner", R.drawable.dinner));
        dataList.add(new DrawerItem("Snacks", R.drawable.snacks));
        dataList.add(new DrawerItem("IT Sligo", R.drawable.itsligo));
        dataList.add(new DrawerItem("Facebook", R.drawable.facebook));
        dataList.add(new DrawerItem("Twitter", R.drawable.twitter));
        dataList.add(new DrawerItem("About", R.drawable.about));


        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0);
        }

        new DownloadJSON().execute();

        //test add tag
        HealthyCampusDataHandler dh = new HealthyCampusDataHandler(getBaseContext());
        Tag t1 = new Tag("breakfast");

        //test add ingredient
        Ingredient i = new Ingredient(1, "flour", 10, "", 1);

        //test add recipe
        Recipe r = new Recipe(1, "pandcake", "lorem ipsum", "lorem ipsum", 1.5, 1.5, 4,"stiff");

        //test add recipetag
        TagRecipes tr = new TagRecipes("breakfast", 1);

        dh.open();

        try {
            t1.InsertIntoDatabase(dh.WritableDb());
            i.InsertIntoDatabase(dh.WritableDb());
            r.InsertIntoDatabase(dh.WritableDb());
            tr.InsertIntoDatabase(dh.WritableDb());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        List<Tag> allTags = Tag.GetAllTagsFromDatabase(dh.WritableDb());
        Tag daT = Tag.GetTagFromDatabase(allTags.get(0).getTagName().toString(), dh.WritableDb());

        List<Ingredient> allIngredients = Ingredient.GetAllIngredientsFromDatabase(dh.WritableDb());
        Ingredient daI = Ingredient.GetIngredientFromDatabase(allIngredients.get(0).getIngredientId(), allIngredients.get(0).getRecipeId(), dh.WritableDb());

        List<Recipe> allRecipes = Recipe.GetAllRecipesFromDatabase(dh.WritableDb());
        Recipe daR = Recipe.GetRecipeFromDatabase(allRecipes.get(0).getRecipeId(), dh.WritableDb());

        List<TagRecipes> allTagRecipes = TagRecipes.GetAllTagRecipesFromDatabase(dh.WritableDb());
        TagRecipes daRT = TagRecipes.GetTagRecipeFromDatabase(allTagRecipes.get(0).getTagName().toString(),
                                                                allTagRecipes.get(0).getRecipeId(), dh.WritableDb());

        dh.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void SelectItem(int possition) {

        Bundle args = new Bundle();

        switch (possition) {
            case 0:
                fragment = new RecipeGrid();
                displayFragment(args);
                break;
            case 1:
                fragment = new RecipeGrid();
                displayFragment(args);
                break;
            case 2:
                fragment = new RecipeGrid();
                displayFragment(args);
                break;
            case 3:
                fragment = new RecipeGrid();
                displayFragment(args);
                break;
            case 4:
                fragment = new RecipeGrid();
                displayFragment(args);
                break;
            case 5:
                displayBrowsers("http://www.itsligo.ie");
                break;
            case 6:
                displayBrowsers("https://www.facebook.com/itsligo");
                break;
            case 7:
                displayBrowsers("https://twitter.com/itsligo");
                break;
            case 8:
                fragment = new FragmentAbout();
                displayFragment(args);
                break;
            default:
                break;
        }



        mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }
    public void displayFragment(Bundle args){
        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
    public void displayBrowsers(String url){
        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setDataAndType(uri, "text/html");
        browserIntent.addCategory(Intent.CATEGORY_BROWSABLE);
        this.startActivity(browserIntent);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        InputStream inputStream = null;
        String result = "";

//        @Override
//        protected Void doInBackground(Void... voids) {
//            return null;
//        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Health Campus");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String recipeJson = sh.makeServiceCall(url_Recipes , ServiceHandler.GET);
            List<Recipe> r = new CreateObjectFromJson().getRecipeObjectsFromJson(recipeJson);

            String recipeTagJson = sh.makeServiceCall(url_Recipe_Tag , ServiceHandler.GET);
            List<TagRecipes> rt = new CreateObjectFromJson().getRecipeTagObjectsFromJson(recipeTagJson);

            String tagJson = sh.makeServiceCall(url_Tags , ServiceHandler.GET);
            List<Tag> t = new CreateObjectFromJson().getTagObjectsFromJson(tagJson);

            String ingredientJson = sh.makeServiceCall(url_Ingredients , ServiceHandler.GET);
            List<Ingredient> i = new CreateObjectFromJson().getIngredientObjectsFromJson(ingredientJson);


            //getBaseContext().deleteDatabase(HealthyCampusDbContract.DATABASE_NAME);

//            HealthyCampusDataHandler dh = new HealthyCampusDataHandler(getBaseContext());
//            Tag t1 = t.get(0);
//            t1.InsertIntoDatabase(dh.WriteableDb());
//
//            List<Tag> allTags = Tag.GetAllTagsFromDatabase(dh.WriteableDb());
//            Tag daT = Tag.GetTagFromDatabase(allTags.get(0).getTagName().toString(), dh.WriteableDb());
//


            result = r.get(0).toString();

            return null;
        }


        @Override
        protected void onPostExecute(Void args) {

            mProgressDialog.setMessage(result);


//            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getApplicationContext());
//
//            dlgAlert.setMessage(result.toString());
//
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

        public ImageDownloader(String url){
            Url = url;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bitmap myBitmap = getBitmapFromURL(Url);

            return null;
        }

        public Bitmap getBitmapFromURL(String link) {
            try {
                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);

                return myBitmap;

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("getBmpFromUrl error: ", e.getMessage().toString());
                return null;
            }
        }
    }

}