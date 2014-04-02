package com.Endanger.healthycampusapp.healthycampus.app;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Endanger.healthycampusapp.healthycampus.app.adapters.ImageAdapter;


/**
 * Created by Enda on 01/04/2014.
 */
public class RecipeGrid extends Fragment {

    public RecipeGrid() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_grid, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(view.getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(v.getContext(), "" + position, Toast.LENGTH_SHORT).show();
                Intent k = new Intent(getActivity(), RecipeActivity.class);
                startActivityForResult(k, 1);
                getActivity().overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
            }
        });
        return view;
    }

}
