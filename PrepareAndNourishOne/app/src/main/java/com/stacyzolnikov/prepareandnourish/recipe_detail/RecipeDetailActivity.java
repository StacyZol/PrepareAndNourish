package com.stacyzolnikov.prepareandnourish.recipe_detail;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stacyzolnikov.prepareandnourish.R;


public class RecipeDetailActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PlaceHolderFragment.OnListItemClickListener {
    private static final String TAG = "RecipeDetailActivity";
    ImageView mRecipePhoto;
    TextView mRecipeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        //Getting the name and photo id for the recipe
        Log.d(TAG, "positionName: "+ intent.getStringExtra("recipeName"));
        String recipeName = intent.getStringExtra("recipeName");
        Log.d(TAG, "positionName2: " + recipeName);
        mRecipeName = (TextView) findViewById(R.id.recipe_title);
        mRecipeName.setText(recipeName);

        int photoID = intent.getIntExtra("recipePhoto",0);
        Log.d(TAG, "positionPhoto: "+ intent.getIntExtra("recipePhoto",0));
        mRecipePhoto = (ImageView) findViewById(R.id.items_header);
        //mRecipePhoto.setImageResource(photoID);

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
                Toast.makeText(RecipeDetailActivity.this, "Sign in!", Toast.LENGTH_SHORT).show();
            }
        });

        Bundle args = new Bundle();
        args.putString("recipeName", recipeName);

        //Getting Fragments
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.items_content_container,
                        MainFragment.newInstance(this)).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void OnListItemClicked(int tabPosition, int listPosition) {

    }
}
