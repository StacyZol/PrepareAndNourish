package com.stacyzolnikov.prepareandnourish.unsigned_users;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stacyzolnikov.prepareandnourish.R;
import com.stacyzolnikov.prepareandnourish.recipe_lists.RecipeListsActivity;
import com.stacyzolnikov.prepareandnourish.setup.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 10/20/16.
 */
public class RecyclerViewUnsignedAdapter extends RecyclerView.Adapter<HomeUnsignedViewHolder> {
    List<Category> mCategory;
    Context mContext;
    LayoutInflater mInflater;

    public RecyclerViewUnsignedAdapter(List<Category> category, Context context) {
        this.mCategory = new ArrayList<>(category);
        this.mContext = context;

    }

    @Override
    public HomeUnsignedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_home_unsigned, parent, false);
        HomeUnsignedViewHolder viewHolder = new HomeUnsignedViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeUnsignedViewHolder holder, final int position) {
        holder.mItemName.setText(mCategory.get(position).getCategoryName());
        holder.mBackgroundPhoto.setImageResource(mCategory.get(position).getCategoryPhoto());
        holder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RecipeListsActivity.class);
                intent.putExtra("category", mCategory.get(position).getCategoryName());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mCategory == null) {
            return 0;
        } else {
            return mCategory.size();
        }
    }
}
