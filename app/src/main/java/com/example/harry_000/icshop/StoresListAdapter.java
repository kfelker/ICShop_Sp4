package com.example.harry_000.icshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class StoresListAdapter  extends ArrayAdapter {


    private Context mContext;
    private List<Store> mStores = new ArrayList<Store>();

    public StoresListAdapter(Context context, List<Store> stores) {
        super(context, R.layout.stores_list, stores);

        mContext = context;
        mStores = stores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.stores_list, null);
        }
        else {
            view = convertView;
        }

        TextView NameView = (TextView) view.findViewById(R.id.Name);

        NameView.setText( mStores.get(position).getName() );


        return view;
    }
}