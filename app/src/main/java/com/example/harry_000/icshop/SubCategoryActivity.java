package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class SubCategoryActivity extends AppCompatActivity {
    private List<Store> Stores = new ArrayList<Store>();
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_stores);

        Intent i= getIntent();
        String SubCategoryID = i.getStringExtra("ProductCategoryID");
        String SubCategoryName = i.getStringExtra("Description");

        //Toast.makeText(getApplicationContext(), id,
        //    Toast.LENGTH_LONG).show();


        db = new MyDatabase(this);
        Stores = db.getStoresByProductCategory(SubCategoryID); // you would not typically call this on the main thread

        StoresListAdapter Adapter = new StoresListAdapter(this, Stores);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(Adapter);

        TextView SubText = (TextView) this.findViewById(R.id.headertext);
        SubText.setText("Stores for:" + SubCategoryName);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                Store clickedObject = (Store) parent.getAdapter().getItem(position);

                String strId = String.valueOf(clickedObject.getID());
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), StoreDetailActivity.class);
                // sending data to new activity
                i.putExtra("storeID", strId);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

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
