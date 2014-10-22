package com.garciaericn.photolocal.fragments;

import com.google.android.gms.maps.MapFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/21/14.
 */
public class PhotoMapFragment extends MapFragment {

    public static final String TAG = "PhotoMapFragment.TAG";

    public PhotoMapFragment() {

    }

    public static PhotoMapFragment getInstance() {
        return new PhotoMapFragment();
    }
}
