
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.Endanger.healthycampusapp.healthycampus.app.adapters.ImageAdapter;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDataHandler;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDbHelper;
import com.Endanger.healthycampusapp.healthycampus.app.database.Recipe;
import com.Endanger.healthycampusapp.healthycampus.app.database.Tag;
import com.Endanger.healthycampusapp.healthycampus.app.database.TagRecipes;


public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    CustomDrawerAdapter adapter;

    HealthyCampusDataHandler dbHandler;
    HealthyCampusDbHelper dbHelper;

    List<DrawerItem> dataList;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //instantiate database accessor
        dbHandler = new HealthyCampusDataHandler(getBaseContext());
        dbHelper = new HealthyCampusDbHelper(getBaseContext());

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
        dataList.add(new DrawerItem("Conversion", R.drawable.convert));
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void SelectItem(int possition) {

        Bundle args = new Bundle();

        switch (possition) {
            case 0:
                fragment = new RecipeGrid(dbHandler, dbHelper, "");
                displayFragment(args);
                break;
            case 1:
                fragment = new RecipeGrid(dbHandler, dbHelper, "Breakfast");
                displayFragment(args);
                break;
            case 2:
                fragment = new RecipeGrid(dbHandler, dbHelper, "Lunch");
                displayFragment(args);
                break;
            case 3:
                fragment = new RecipeGrid(dbHandler, dbHelper, "Dinner");
                displayFragment(args);
                break;
            case 4:
                fragment = new RecipeGrid(dbHandler, dbHelper, "Snack");
                displayFragment(args);
                break;
            case 5:
                fragment = new FragmentConversionTable();
                displayFragment(args);
                break;
            case 6:
                displayBrowsers("http://www.itsligo.ie");
                break;
            case 7:
                displayBrowsers("https://www.facebook.com/itsligo");
                break;
            case 8:
                displayBrowsers("https://twitter.com/itsligo");
                break;
            case 9:
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

    public static class RecipeGrid extends Fragment {

        HealthyCampusDataHandler dHandler;
        HealthyCampusDbHelper dHelper;
        String theTag;

        List<Recipe> AllRecipes;
        List<Recipe> RecipesForGrid;
        List<TagRecipes> AllTags;
        List<Integer> TagsWithRecipeIds;

        public RecipeGrid(){
        }

        public RecipeGrid(HealthyCampusDataHandler hcdhandler, HealthyCampusDbHelper hcdhelper, String tag){
            dHandler = hcdhandler;
            dHelper = hcdhelper;
            theTag = tag;

            dHandler.open();
            AllRecipes = Recipe.GetAllRecipesFromDatabase(dHandler.WritableDb());
            dHandler.close();

            dHandler.open();
            AllTags = TagRecipes.GetAllTagRecipesFromDatabase(dHandler.WritableDb());
            dHandler.close();

            RecipesForGrid = new ArrayList<Recipe>();
            TagsWithRecipeIds = new ArrayList<Integer>();
            if(theTag == "")
            {
                RecipesForGrid = AllRecipes;
            }
            else {
                for (int i = 0; i < AllTags.size(); i++) {
                    if (AllTags.get(i).getTagName().equalsIgnoreCase(theTag)) {
                        TagsWithRecipeIds.add(AllTags.get(i).getRecipeId());
                    }
                }

                if (TagsWithRecipeIds != null) {
                    for (int i = 0; i < AllRecipes.size(); i++) {
                        if (TagsWithRecipeIds.contains(AllRecipes.get(i).getRecipeId())) {
                            RecipesForGrid.add(AllRecipes.get(i));
                        }
                    }
                }
            }
        }

        public static final RecipeGrid newInstance(HealthyCampusDataHandler hcdhandler, HealthyCampusDbHelper hcdhelper, String tag)
        {
            RecipeGrid fragment = new RecipeGrid(hcdhandler, hcdhelper, tag);
            Bundle args = new Bundle();
            fragment.setArguments(args);

            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            final View view = inflater.inflate(R.layout.fragment_recipe_grid, container, false);
            final ViewGroup viewGroup = container;

            GridView gridView = (GridView) view.findViewById(R.id.gridview);

            final ImageAdapter i = new ImageAdapter(view.getContext(), dHandler, dHelper, RecipesForGrid);
            gridView.setAdapter(i);

//            new Thread(new Runnable()
//            {
//                int time = 0;
//                @Override
//                public void run()
//                {
//                    while (time < 30) {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (Exception e) {
//
//                        }
//
//                        try {
//                            i.notifyDataSetChanged();
//                        } catch (Exception e) {
//
//                        }
//                    }
//                    Thread.currentThread().interrupt();
//                }
//            }).start();

//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                    Intent k = new Intent(getActivity(), RecipeActivity.class);
//                    String recipeId = ((TextView)view.findViewById(R.id.hiddenRecipeId)).getText().toString();
//
//                    //im sincerely sorry
//                    recipeId = ((TextView)(((GridView)view.findViewById(R.id.gridview)).getChildAt(position)).findViewById(R.id.hiddenRecipeId)).getText().toString();
//
//                    k.putExtra("RecipeId", recipeId);
//                    startActivityForResult(k, 1);
//
//                    getActivity().overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in);
//                }
//            });

            return view;
        }

    }

}