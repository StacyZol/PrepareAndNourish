package com.stacyzolnikov.prepareandnourish.recipe_detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by stacyzolnikov on 11/8/16.
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {
    int mPageCount;
    Context mContext;
    public PlaceHolderFragment.OnListItemClickListener mItemClickListener;

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SectionPagerAdapter(FragmentManager fm, int pageCount, PlaceHolderFragment.OnListItemClickListener listener) {
        super(fm);
        mItemClickListener = listener;
        mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceHolderFragment.newInstance(position, mItemClickListener);
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
    public CharSequence getPageTitle(int position){
        switch(position){
            default:
            case 0:
                return "Overview";
            case 1:
                return "Ingredients";
            case 2:
                return "Directions";
        }
    }
}
