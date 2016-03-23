package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class StoreDetailActivity extends AppCompatActivity{
    private MyDatabase db;
    private Store myStore;
    private String strBrands;
    private String strCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        Intent i = getIntent();
        String id = i.getStringExtra("storeID");




        db = new MyDatabase(this);
        myStore = db.getStore(id);
        TextView store = (TextView) this.findViewById(R.id.textViewStore);
        store.setText(myStore.getName());

        TextView address = (TextView) this.findViewById(R.id.textViewAddress);
        address.setText(myStore.getAddress());

        TextView hours = (TextView) this.findViewById(R.id.textViewHours);
        hours.setText(myStore.getHours());

        TextView phone = (TextView) this.findViewById(R.id.textViewPhone);
        phone.setText(myStore.getPhone());

        db.close();

        TabHost tabHost = (TabHost) findViewById(R.id.mytabhost);
        tabHost.setup();
        TabSpec tab1 = tabHost.newTabSpec("TAB_1");
        tab1.setIndicator("BRANDS");
        tab1.setContent(R.id.Brands);
        tabHost.addTab(tab1);

        TabSpec tab2 = tabHost.newTabSpec("TAB_2");
        tab2.setIndicator("CATEGORIES");
        tab2.setContent(R.id.Categories);
        tabHost.addTab(tab2);

        db = new MyDatabase(this);
        strBrands = db.getStrBrandsForStore(id);
        TextView brands = (TextView) this.findViewById(R.id.textViewBrands);
        brands.setText(strBrands);

        db.close();

        db = new MyDatabase(this);
        strCategories = db.getStrCategoriesForStore(id);
        TextView cats = (TextView) this.findViewById(R.id.textViewCategories);
        cats.setText(strCategories);


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

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}