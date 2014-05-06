package com.Endanger.healthycampusapp.healthycampus.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Endanger.healthycampusapp.healthycampus.app.R;
import com.Endanger.healthycampusapp.healthycampus.app.RecipeActivity;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDataHandler;
import com.Endanger.healthycampusapp.healthycampus.app.database.HealthyCampusDbHelper;
import com.Endanger.healthycampusapp.healthycampus.app.database.Recipe;
import com.Endanger.healthycampusapp.healthycampus.app.database.Tag;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Enda on 23/03/2014.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;

    HealthyCampusDataHandler dHandler;
    HealthyCampusDbHelper dHelper;

    List<Recipe> Recipes;
    List<Tag> tags;


    public ImageAdapter(Context c, HealthyCampusDataHandler hcdhandler, HealthyCampusDbHelper hcdhelper, List<Recipe> recipes) {
        mContext = c;
        layoutInflater = LayoutInflater.from(c);

        dHandler = hcdhandler;
        dHelper = hcdhelper;

//        dHandler.open();
//        recipes = Recipe.GetAllRecipesFromDatabase(dHandler.WritableDb());
//        dHandler.close();
//
//        dHandler.open();
//        tags = Tag.GetAllTagsFromDatabase(dHandler.WritableDb());
//        dHandler.close();

        Recipes = recipes;
    }

    public int getCount() {
        return Recipes.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {  // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(-1, 350));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//        return imageView;
        // TODO Auto-generated method stub
        View view;
        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.grid_image, parent, false);
                    //layoutInflater.inflate(R.layout.grid_image, null);
        }
        else
            view = convertView;

        Recipe r = Recipes.get(position);

        TextView hiddenID = (TextView)view.findViewById(R.id.hiddenRecipeId);
        final int recipeId = r.getRecipeId();
        hiddenID.setText(String.valueOf(recipeId));

        ImageView image = (ImageView)view.findViewById(R.id.image);
        //image.setImageBitmap(r.GetImage(mContext));
        //image.setImageResource(R.drawable.defaultmealimage);


        Picasso.with(mContext).load(r.getImageURL()).placeholder(R.drawable.defaultmealimage).resize(300, 300).into(image);
        Picasso.with(mContext).setDebugging(true);

        TextView text = (TextView)view.findViewById(R.id.title);
        text.setText(r.getTitle() != null ? r.getTitle() : "Unknown" );


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(mContext, RecipeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);;
                Activity activity = (Activity) mContext;
                k.putExtra("RecipeId", String.valueOf(recipeId));
                mContext.startActivity(k);

                activity.overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in);
            }
        });
            return  view;
    }
}