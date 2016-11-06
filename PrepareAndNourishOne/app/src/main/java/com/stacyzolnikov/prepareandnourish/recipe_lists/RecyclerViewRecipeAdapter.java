package com.stacyzolnikov.prepareandnourish.recipe_lists;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stacyzolnikov.prepareandnourish.R;
import com.stacyzolnikov.prepareandnourish.reciple_detail.RecipeDetailActivity;

import java.util.List;

/**
 * Created by stacyzolnikov on 10/25/16.
 */
public class RecyclerViewRecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    List<Recipe> mRecipeList;
    Context mContext;

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
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RecipeDetailActivity.class);
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
