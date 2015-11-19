package com.example.harry_000.icshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ArrayAdapter;


/**
 * Created by Felker on 10/11/2015.
 */
public class MainCategoryAdapter extends ArrayAdapter<MainRetailCategory> {
    private Context context;
    private List<MainRetailCategory> mainCatList;

    public MainCategoryAdapter(Context context, int textViewResourceId,
                               List<MainRetailCategory> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.mainCatList = values;
    }

    public int getCount(){
        return mainCatList.size();
    }

    public MainRetailCategory getItem(int position){
        return mainCatList.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setTextColor(Color.BLACK);
        view.setGravity(Gravity.CENTER);
        view.setText(mainCatList.get(position).getDesc());

        return view;
    }

    //View of Spinner on dropdown Popping

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView view = new TextView(context);
        view.setTextColor(Color.BLACK);
        view.setText(mainCatList.get(position).getDesc());
        view.setHeight(60);

        return view;
    }

}