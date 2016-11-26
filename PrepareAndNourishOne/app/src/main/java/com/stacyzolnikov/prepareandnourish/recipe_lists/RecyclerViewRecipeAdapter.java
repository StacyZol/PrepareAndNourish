package com.stacyzolnikov.prepareandnourish.recipe_lists;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stacyzolnikov.prepareandnourish.R;
import com.stacyzolnikov.prepareandnourish.recipe_detail.MainFragment;
import com.stacyzolnikov.prepareandnourish.recipe_detail.RecipeDetailActivity;
import com.stacyzolnikov.prepareandnourish.setup.DatabaseHelper;

import java.util.List;

/**
 * Created by stacyzolnikov on 10/25/16.
 */
public class RecyclerViewRecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    List<Recipe> mRecipeList;
    Context mContext;
    private static final String TAG = "positionOne";
    public RecyclerViewRecipeAdapter(List<Recipe> mRecipeList, Context mContext) {
        this.mRecipeList = mRecipeList;
        this.mContext = mContext;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_recipe, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.mRecipeName.setText(mRecipeList.get(position).getRecipeName());
        holder.mRecipeBackground.setImageResource(mRecipeList.get(position).getRecipePhoto());
        //Below is getting the string of the recipe name so we can pass it to the next activity
        //Need to do the same for photo
        final String recipeName = mRecipeList.get(position).getRecipeName();
        final int recipePhoto = mRecipeList.get(position).getRecipePhoto();
        Log.d(TAG, "Recipe/Photo: "+ mRecipeList.get(position).getRecipePhoto());
        Log.d(TAG, "Recipe/Photo: "+ mRecipeList.get(position).getRecipeName());
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RecipeDetailActivity.class);
                intent.putExtra("recipeName",recipeName);
                intent.putExtra("recipePhoto", recipePhoto);
                Log.i(TAG, "recipeName: " + recipeName);
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (mRecipeList == null) {
            return 0;
        } else {
            return mRecipeList.size();
        }
    }

}
