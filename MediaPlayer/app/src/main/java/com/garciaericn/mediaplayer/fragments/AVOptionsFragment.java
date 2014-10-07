package com.garciaericn.mediaplayer.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.garciaericn.mediaplayer.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/4/14.
 */
public class AVOptionsFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "AVOptionsFragment.TAG";
    private AVOptionsFragmentCallbacks activity;

    public interface AVOptionsFragmentCallbacks{
        public void loadAudio();
    }

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

        // Set onClickListeners
        Button audioButton = (Button) view.findViewById(R.id.audio_button);
        audioButton.setOnClickListener(this);

        Button videoButton = (Button) view.findViewById(R.id.video_button);
        videoButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AVOptionsFragmentCallbacks) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audio_button:
                Log.i(TAG, "Audio button pressed");
                activity.loadAudio();
                break;
            default:
                break;
        }
    }
}
