package com.stacyzolnikov.prepareandnourish.recipe_lists;

import android.content.Intent;
import android.provider.ContactsContract;
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
import com.stacyzolnikov.prepareandnourish.unsigned_users.Category;
import com.stacyzolnikov.prepareandnourish.unsigned_users.RecyclerViewUnsignedAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecipeListsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView mRecyclerViewRecipeList;
    DatabaseHelper mDatabaseHelper;
    List<Recipe> recipeList;
    RecyclerViewRecipeAdapter mAdapter;
    private static final String TAG = "RecipeListsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_lists);
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
        navigationView.setNavigationItemSelectedListener( this);

        //Floating Action button. Will prompt user to sign in for now
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_unsigned);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(RecipeListsActivity.this, "Sign in!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recipeList = new ArrayList<>();

        mRecyclerViewRecipeList = (RecyclerView) findViewById(R.id.recycler_view_unsigned);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewRecipeList.setLayoutManager(linearLayoutManager);

        mDatabaseHelper = DatabaseHelper.getInstance(RecipeListsActivity.this);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        Log.i(TAG, "category: "+ category);

        //Checking to see if the recipe list is empty, if so, adding the lists
        mDatabaseHelper.checkRecipeDatabase();

        if (category.equals("Breakfast")){
            recipeList = mDatabaseHelper.getBreakfast();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }
        if (category.equals("Entrees")){
            recipeList = mDatabaseHelper.getEntrees();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }
        if (category.equals("Drinks")){
            recipeList = mDatabaseHelper.getDrinks();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }
        if (category.equals("Salads")){
            recipeList = mDatabaseHelper.getSalads();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }
        if (category.equals("Snacks")){
            recipeList = mDatabaseHelper.getSnacks();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }
        if (category.equals("Ferments")){
            recipeList = mDatabaseHelper.getFerments();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }
        if (category.equals("Sides")){
            recipeList = mDatabaseHelper.getSides();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }
        if (category.equals("Sweets")){
            recipeList = mDatabaseHelper.getSweets();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }
        if (category.equals("Soups")){
            recipeList = mDatabaseHelper.getSoups();
            mAdapter = new RecyclerViewRecipeAdapter(recipeList, this);
            mRecyclerViewRecipeList.setAdapter(mAdapter);
        }


        // recipeList = mDatabaseHelper.getBreakfast();

        Log.i(TAG, "onResume: " + recipeList.size());

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
