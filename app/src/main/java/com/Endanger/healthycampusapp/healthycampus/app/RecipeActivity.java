package com.Endanger.healthycampusapp.healthycampus.app;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothClass;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.Endanger.healthycampusapp.healthycampus.app.adapters.ImageAdapter;
import com.Endanger.healthycampusapp.healthycampus.app.adapters.RecipeIngredientChecklistAdapter;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDataHandler;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDbHelper;
import com.Endanger.healthycampusapp.healthycampus.app.database.Ingredient;
import com.Endanger.healthycampusapp.healthycampus.app.database.Recipe;

import org.w3c.dom.Text;


public class RecipeActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    HealthyCampusDataHandler dbHandler;
    HealthyCampusDbHelper dbHelper;

    Recipe recipe;

    List<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        dbHandler = new HealthyCampusDataHandler(getBaseContext());
        dbHelper = new HealthyCampusDbHelper(getBaseContext());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String recipeId = extras.getString("RecipeId");
            dbHandler.open();
            recipe = Recipe.GetRecipeFromDatabase(Integer.parseInt(recipeId), dbHandler.WritableDb());
            dbHandler.close();
        }

        dbHandler.open();
        ingredients = Ingredient.GetAllIngredientsForRecipeFromDatabase(dbHandler.WritableDb(),recipe.getRecipeId());
        dbHandler.close();

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setHomeButtonEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
        onBackPressed();
        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        int scrHeight = getWindowManager().getDefaultDisplay().getHeight();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            if(position == 1)
                return IngredientFragment.newInstance(position + 1, ingredients);
            else if(position == 2)
                return MethodFragment.newInstance(position + 1, recipe);
            else
                return DescriptionFragment.newInstance(position + 1, getWindowManager().getDefaultDisplay().getHeight(), recipe);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//    }
    public static class DescriptionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static int DeviceHeight = 200;

        private Recipe recipe;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static DescriptionFragment newInstance(int sectionNumber, int deviceHeight, Recipe recipe) {
            DescriptionFragment fragment = new DescriptionFragment(recipe);
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            DeviceHeight = (int)deviceHeight/3;
            return fragment;
        }

        public DescriptionFragment(Recipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_recipeactivity_description, container, false);

            ImageView headerImage = (ImageView)rootView.findViewById(R.id.recipeoverviewimageView);
            headerImage.setImageBitmap(recipe.GetImage(rootView.getContext()));

            TextView overviewTitle = (TextView)rootView.findViewById(R.id.overviewTitle);
            overviewTitle.setText(recipe.getTitle());

            TextView overviewDescription = (TextView)rootView.findViewById(R.id.txtDescription);
            overviewDescription.setText(recipe.getDescription());

            TextView overviewDifficulty = (TextView)rootView.findViewById(R.id.txtDifficulty);
            overviewDifficulty.setText(recipe.getDifficultyLevel() + " / 10");

            TextView overviewPrepTime = (TextView)rootView.findViewById(R.id.txtPreptime);
            overviewPrepTime.setText(recipe.getPrepTime() + " min");

            TextView overviewCookTime = (TextView)rootView.findViewById(R.id.txtCookTime);
            overviewCookTime.setText(recipe.getCookTime() + " min");

            ImageView image = (ImageView)rootView.findViewById(R.id.recipeoverviewimageView);
            image.getLayoutParams().height = DeviceHeight;

            return rootView;
        }
    }

    public static class MethodFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static Recipe recipe;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MethodFragment newInstance(int sectionNumber, Recipe theRecipe) {
            MethodFragment fragment = new MethodFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            recipe = theRecipe;

            return fragment;
        }

        public MethodFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_recipeactivity_method, container, false);

            TextView textView = (TextView) rootView.findViewById(R.id.txtMethod);
            textView.setText(recipe.getMethod());

            //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public static class IngredientFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static List<Ingredient> Ingredients;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static IngredientFragment newInstance(int sectionNumber, List<Ingredient> ingredients) {
            IngredientFragment fragment = new IngredientFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            Ingredients = ingredients;

            return fragment;

        }

        public IngredientFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_recipeactivity_ingredients, container, false);

            GridView gridView = (GridView) rootView.findViewById(R.id.IngredientsGrid);

            gridView.setAdapter(new RecipeIngredientChecklistAdapter(rootView.getContext(), Ingredients));

            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


}
