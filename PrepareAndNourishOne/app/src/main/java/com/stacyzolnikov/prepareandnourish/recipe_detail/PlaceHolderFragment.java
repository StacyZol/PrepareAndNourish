package com.stacyzolnikov.prepareandnourish.recipe_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stacyzolnikov.prepareandnourish.R;
import com.stacyzolnikov.prepareandnourish.setup.DatabaseHelper;

/**
 * Created by stacyzolnikov on 11/8/16.
 */
public class PlaceHolderFragment extends Fragment {

    Context mContext;
    private int tabPosition;
    public OnListItemClickListener mListItemClickListener;
    RecyclerView mRecyclerViewOverview;
    RecyclerView mRecyclerViewDirections;
    RecyclerView mRecyclerViewIngredients;

    public interface OnListItemClickListener{
        void OnListItemClicked(int tabPosition,int listPosition);

    }

    public static PlaceHolderFragment newInstance(int tab_number, OnListItemClickListener listener){
        PlaceHolderFragment placeHolderFragment = new PlaceHolderFragment();
        placeHolderFragment.tabPosition = tab_number;
        placeHolderFragment.mListItemClickListener = listener;
        return placeHolderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        mContext = getActivity();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mContext);
        switch (tabPosition){
            case 0:
                view = inflater.inflate(R.layout.fragment_overview, container, false);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_ingredients, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_directions, container, false);
                break;

        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
