package com.stacyzolnikov.prepareandnourish.recipe_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stacyzolnikov.prepareandnourish.R;

/**
 * Created by stacyzolnikov on 11/7/16.
 */
public class MainFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    public SectionPagerAdapter mSectionPagerAdapter;
    public static PlaceHolderFragment.OnListItemClickListener mItemListener;
    public CollapsingToolbarLayout mCollapsingToolbarLayout;
    public ImageView mRecipePhoto;
    public TextView mRecipeName;
    private static final String TAG = "MainFragment";

    public static Fragment newInstance(PlaceHolderFragment.OnListItemClickListener listener){
        MainFragment fragment = new MainFragment();
        fragment.mItemListener = listener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        mViewPager = (ViewPager) rootview.findViewById(R.id.items_viewpager);
        mTabLayout = (TabLayout) rootview.findViewById(R.id.items_tabs);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mRecipePhoto = (ImageView) rootview.findViewById(R.id.items_header);
        //Getting the name and photo id for the recipe
        mRecipePhoto.setImageResource(0);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) rootview.findViewById(R.id.items_collapse_toolbar);

        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSectionPagerAdapter = new SectionPagerAdapter(getFragmentManager(), 3, mItemListener);
        mViewPager.setAdapter(mSectionPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

        }
        return super.onOptionsItemSelected(item);
    }
}
