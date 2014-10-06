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
 * Created by ENG618-Mac on 10/5/14.
 */
public class AudioPlayerFragment extends Fragment {

    public static final String TAG = "AudioPlayerFragment.TAG";

    private static String packageName;
    private static AudioPlayerFragment audioPlayerFragment;

    public AudioPlayerFragment() {

    }

    public static AudioPlayerFragment newInstance(String packageName) {
        AudioPlayerFragment.packageName = packageName;

        if (audioPlayerFragment == null) {
            audioPlayerFragment = new AudioPlayerFragment();
        }

        return audioPlayerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");

        //Load layout
        View view = inflater.inflate(R.layout.fragment_audio_player, container, false);

//        String demoVideoString = "android.resource://" + packageName + "/" + R.raw.demo;

        return view;
    }
}
