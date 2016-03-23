package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryExpandActivity extends AppCompatActivity {

    private List<MainRetailCategory> mainList = new ArrayList<MainRetailCategory>();
    // private List<Store> Stores = new ArrayList<Store>();
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_expand);

        mainList = createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        CategoryExpandAdapter adapter = new CategoryExpandAdapter(this,mainList);
        listView.setAdapter(adapter);

        ////////////////////////////////////////////////////////////////////////////////////////////


        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // selected item
                //Store clickedObject = (Store) parent.getAdapter().getItem(childPosition);

                // String description = String.valueOf(clickedObject.getName());

                MainRetailCategory headerInfo = mainList.get(groupPosition);
                //get the child info
                SubRetailCategory detailInfo =  headerInfo.getSubCatList().get(childPosition);



                String strid = String.valueOf(detailInfo.getID());


                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), SubCategoryActivity.class);
                // sending data to new activity
                i.putExtra("ProductCategoryID", strid);
                i.putExtra("Description", detailInfo.getDesc());
                startActivity(i);

                return true;


            }
        });


    }

    /////////////////////////////////////////////////////////////////////////////////////////////


  /*  @Override


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_brand_stores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */

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

    public List<MainRetailCategory> createData() {
        List<MainRetailCategory> mList = new ArrayList<MainRetailCategory>();
        List<SubRetailCategory> subList = new ArrayList<SubRetailCategory>();
        db = new MyDatabase(getApplicationContext());
        mList = db.getAllMainCategories();

        for (int i = 0; i < mList.size(); i++) {
            String id = String.valueOf(mList.get(i).getID());
            subList = db.getAllSubCategories(id);
            mList.get(i).setSubCatList(subList);


        /*for (int j = 0; j < 5; j++) {
            Group group = new Group("Test " + j);
            for (int i = 0; i < 5; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }*/
        }
        return mList;
    }
}