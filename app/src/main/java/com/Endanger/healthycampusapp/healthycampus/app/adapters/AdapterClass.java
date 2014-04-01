package com.Endanger.healthycampusapp.healthycampus.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.Endanger.healthycampusapp.healthycampus.app.R;

import java.util.ArrayList;

/**
 * Created by Enda on 23/03/2014.
 */
public class AdapterClass extends ArrayAdapter<String> {
    Context context;
    private ArrayList<String> TextValue = new ArrayList<String>();

    public AdapterClass(Context context, ArrayList<String> TextValue) {
        super(context, R.layout.drawer_row, TextValue);
        this.context = context;
        this.TextValue= TextValue;
    }


    @Override
    public View getView(int position, View coverView, ViewGroup parent) {
        // TODO Auto-generated method stub

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.drawer_row,
                parent, false);

        TextView text1 = (TextView)rowView.findViewById(R.id.title);
        text1.setText(TextValue.get(position));

        return rowView;

    }

}