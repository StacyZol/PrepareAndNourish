package com.stacyzolnikov.prepareandnourish.recipe_lists;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stacyzolnikov.prepareandnourish.R;

/**
 * Created by stacyzolnikov on 10/25/16.
 */
public class RecipeViewHolder extends RecyclerView.ViewHolder {
    public TextView mRecipeName;
    public ImageView mRecipeBackground;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        mRecipeName = (TextView) itemView.findViewById(R.id.recipe_text);
        mRecipeBackground = (ImageView) itemView.findViewById(R.id.recipe_image);
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        itemView.setOnClickListener(onClickListener);
    }


}
