package com.example.eng_mahnoud83coffey.listen_readandsave;

/**
 * Created by eng-mahnoud on 27/02/18.
 */
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int logos[];
    String namelogo[];
    LayoutInflater inflter;
    public CustomAdapter(Context applicationContext, int[] logos,String namelogo[]) {
        this.context = applicationContext;
        this.logos = logos;
        this.namelogo=namelogo;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.length;
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
        view = inflter.inflate(R.layout.row_grid_view, null); // inflate the layout
        ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
        TextView name=(TextView)view.findViewById(R.id.textcardview);
        icon.setImageResource(logos[i]); // set logo images
        name.setText(namelogo[i]);
        return view;
    }
}