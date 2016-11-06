package com.stacyzolnikov.prepareandnourish.unsigned_users;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stacyzolnikov.prepareandnourish.R;

/**
 * Created by stacyzolnikov on 10/20/16.
 */
public class HomeUnsignedViewHolder extends RecyclerView.ViewHolder {
    public TextView mItemName;
    public ImageView mBackgroundPhoto;

    public HomeUnsignedViewHolder(View itemView) {
        super(itemView);
        mItemName = (TextView) itemView.findViewById(R.id.home_title);
        mBackgroundPhoto = (ImageView) itemView.findViewById(R.id.home_background_unsigned);

    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        itemView.setOnClickListener(onClickListener);
    }
}
