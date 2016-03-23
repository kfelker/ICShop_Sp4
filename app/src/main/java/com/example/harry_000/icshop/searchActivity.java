package com.example.harry_000.icshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanxiao on 10/27/15.
 */
public class searchActivity extends AppCompatActivity {

    private MyDatabase db;
    private List<Store> Stores = new ArrayList<Store>();
    private List<Brand> Brands = new ArrayList<Brand>();
    private Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        final Button searchBtn=(Button) findViewById(R.id.button);
        final TextView keywordText = (TextView) this.findViewById(R.id.textView);
        final RadioButton radio_Brand=(RadioButton) findViewById(R.id.radioButton_Brand);
        final RadioButton radio_Store=(RadioButton) findViewById(R.id.radioButton_Store);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (radio_Brand.isChecked()) {
                   mcontext = getApplicationContext();
                   db = new MyDatabase(mcontext);
                   String key = keywordText.getText().toString();

                   Brands = db.getBrandsBySearch(key);
                   ArrayAdapter<Brand> adapter = new ArrayAdapter<Brand>(mcontext,
                           android.R.layout.simple_list_item_1, Brands);
                   ListView list = (ListView) findViewById(android.R.id.list);

                   list.setAdapter(adapter);

                   list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       public void onItemClick(AdapterView<?> parent, View view,
                                               int position, long id) {

                           // selected item
                           Brand clickedObject = (Brand) parent.getAdapter().getItem(position);

                           String strid = String.valueOf(clickedObject.getID());
                           String strBrand = clickedObject.getName();
                           // Launching new Activity on selecting single List Item
                           Intent i = new Intent(getApplicationContext(), BrandStoresActivity.class);
                           // sending data to new activity
                           i.putExtra("brandID", strid);
                           i.putExtra("brandName", strBrand);
                           startActivity(i);

                       }

                   });
               }
                if (radio_Store.isChecked()){

                    mcontext = getApplicationContext();
                    db = new MyDatabase(mcontext);
                    String key = keywordText.getText().toString();

                Stores = db.getStoresBySearch(key);
                ArrayAdapter<Store> adapter = new ArrayAdapter<Store>(mcontext,
                        android.R.layout.simple_list_item_1, Stores);
                ListView list = (ListView) findViewById(android.R.id.list);
                list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            // selected item
                            Store clickedObject = (Store) parent.getAdapter().getItem(position);

                            String strStoreid = String.valueOf(clickedObject.getID());
                            //String strStore = clickedObject.getName();
                            // Launching new Activity on selecting single List Item
                            Intent i = new Intent(getApplicationContext(), StoreDetailActivity.class);
                            // sending data to new activity
                            i.putExtra("storeID", strStoreid);
                           //i.putExtra("brandName", strStore);
                            startActivity(i);

                    }
                });

                }
                if ((radio_Brand.isChecked()==false) & (radio_Store.isChecked()==false)){
                    String notification="Please select Brand/Store";
                    Toast.makeText(searchActivity.this,notification, Toast.LENGTH_SHORT).show();
                }
            }

        });

    }


    // button.setOnClickListener(new View.OnClickListener() {

    // @Override
    //public void onClick(View view) {
    //  String keyword = searchText.getText().toString();

    //}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_Brand:
                //Create Intent for Shopping List Activity
                Intent listIntent = new Intent(this, BrandListActivity.class);
                //Start Product Activity
                startActivity(listIntent);
                return true;
            case R.id.action_Category:
                //Create Intent for Product Activity
                Intent productIntent = new Intent(this,CategoryExpandActivity.class);
                //Start Product Activity
                startActivity(productIntent);
                return true;
            case R.id.action_Search:
                Intent searchIntent = new Intent(this, searchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.action_Stores:
                Intent storesIntent = new Intent(this, StoreListActivity.class);
                startActivity(storesIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
