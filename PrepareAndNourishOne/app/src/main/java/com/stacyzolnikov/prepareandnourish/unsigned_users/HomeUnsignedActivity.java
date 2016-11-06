package com.stacyzolnikov.prepareandnourish.unsigned_users;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.stacyzolnikov.prepareandnourish.R;
import com.stacyzolnikov.prepareandnourish.setup.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeUnsignedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView mRecyclerViewUnsigned;
    DatabaseHelper mDatabaseHelper;
    List<Category> arrayList;
    RecyclerViewUnsignedAdapter mAdapter;

    private static final String TAG = "HomeUnsignedActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_unsigned_user);
        //Using a different toolbar for Unsigned Users
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarUnsigned);
        setSupportActionBar(toolbar);

        //Using a different drawer Layout for Unsigned Users. Idea to get people to pay and sign up
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unsigned);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Navigation view is also different if they are unsigned
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_unsigned);
        navigationView.setNavigationItemSelectedListener(this);

        //Floating Action button. Will prompt user to sign in for now
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_unsigned);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(HomeUnsignedActivity.this, "Sign in!", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        //Need to put progressbar here




    }

    @Override
    protected void onResume() {
        super.onResume();
        //Put everything here
        //Adding data to database
        //Need to add method to check to see if the database is empty, then add to database
        arrayList = new ArrayList<>();


        mRecyclerViewUnsigned = (RecyclerView) findViewById(R.id.recycler_view_unsigned);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerViewUnsigned.setLayoutManager(linearLayoutManager);

        mDatabaseHelper = DatabaseHelper.getInstance(HomeUnsignedActivity.this);
        mDatabaseHelper.addCategories();
        arrayList = mDatabaseHelper.getCategories();

        mAdapter = new RecyclerViewUnsignedAdapter(arrayList, this);
        mRecyclerViewUnsigned.setAdapter(mAdapter);
        Log.i(TAG, "onResume: " + arrayList.size());

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return false;
    }
}
