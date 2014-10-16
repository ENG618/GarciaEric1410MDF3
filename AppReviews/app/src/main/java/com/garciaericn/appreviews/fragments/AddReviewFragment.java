package com.garciaericn.appreviews.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciaericn.appreviews.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/15/14.
 */
public class AddReviewFragment extends Fragment {

    public static final String TAG = "AddReviewFragment.TAG";

    public AddReviewFragment() {

    }

    public static AddReviewFragment newInstance(){
        return new AddReviewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");

        View view = inflater.inflate(R.layout.fragment_add_review, container, false);

        return view;
    }
}
