package com.garciaericn.mediaplayer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/4/14.
 */
public class VideoPlayerFragment extends Fragment {

    public static final String TAG = "VideoPlayerFragment.TAG";

    public VideoPlayerFragment() {

    }

    public static VideoPlayerFragment newInstance() {
        VideoPlayerFragment vpf = new VideoPlayerFragment();
        return vpf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
