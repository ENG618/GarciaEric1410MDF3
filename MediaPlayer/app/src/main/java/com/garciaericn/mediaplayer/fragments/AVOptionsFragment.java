package com.garciaericn.mediaplayer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciaericn.mediaplayer.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/4/14.
 */
public class AVOptionsFragment extends Fragment {

    public static final String TAG = "AVOptionsFragment.TAG";

    public AVOptionsFragment() {

    }

    public static AVOptionsFragment newInstance() {
        AVOptionsFragment fragment = new AVOptionsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");
        //Load layout
        View view = inflater.inflate(R.layout.fragment_av_options, container, false);
        return view;
    }
}
