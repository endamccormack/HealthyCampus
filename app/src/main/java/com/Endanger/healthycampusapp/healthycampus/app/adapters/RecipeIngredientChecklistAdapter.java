package com.Endanger.healthycampusapp.healthycampus.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.Endanger.healthycampusapp.healthycampus.app.R;
import com.Endanger.healthycampusapp.healthycampus.app.database.Ingredient;

import java.util.List;

/**
 * Created by endamccormack on 21/04/2014.
 */
public class RecipeIngredientChecklistAdapter extends BaseAdapter {
    Context context;
    List<Ingredient> ingredients;
    private LayoutInflater layoutInflater;

    public RecipeIngredientChecklistAdapter(Context context, List<Ingredient> ingredients){

        layoutInflater = LayoutInflater.from(context);

        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView;
        if(view == null) {
            newView = layoutInflater.inflate(R.layout.recipe_ingredient_checklist_item, null);
        }
        else
            newView = view;

        TextView title = (TextView)newView.findViewById(R.id.checkboxIngredientName);
        title.setText(ingredients.get(i).getName());

        TextView amount = (TextView)newView.findViewById(R.id.checkboxIngredientAmount);
        amount.setText(ingredients.get(i).getAmountInGrams() + " " + ingredients.get(i).getIngredientType());

        newView.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox check = (CheckBox)v.findViewById(R.id.checkbox);
                        if (check.isChecked()) {
                            check.setChecked(false);
                        }
                        else{
                            check.setChecked(true);
                        }
                    }
                }
        );

        return newView;
    }
}
