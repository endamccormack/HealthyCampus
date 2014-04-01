package com.Endanger.healthycampusapp.healthycampus.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.Endanger.healthycampusapp.healthycampus.app.R;

/**
 * Created by Enda on 23/03/2014.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;

    public ImageAdapter(Context c) {
        mContext = c;
        layoutInflater = LayoutInflater.from(c);
    }

    public int getCount() {
        return mThumbIds.length;
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
            view = layoutInflater.inflate(R.layout.grid_image, null);
        }
        else
            view = convertView;

        ImageView image = (ImageView)view.findViewById(R.id.image);
        image.setImageResource(this.mThumbIds[position]);
        //image.setLayoutParams(new GridView.LayoutParams(-1, 350));
        //image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView text = (TextView)view.findViewById(R.id.title);
        text.setText("DAWWWGY");

        return  view;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
}