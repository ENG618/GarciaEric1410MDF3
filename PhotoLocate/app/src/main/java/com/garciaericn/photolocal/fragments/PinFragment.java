package com.garciaericn.photolocal.fragments;

import android.app.Fragment;

import com.garciaericn.photolocal.data.Pin;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/22/14.
 */
public class PinFragment extends Fragment {

    public PinFragment() {

    }

    public static PinFragment getInstance(Pin pin) {
        return new PinFragment();
    }
}
