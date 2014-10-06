package com.garciaericn.mediaplayer.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.garciaericn.mediaplayer.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */
public class AudioPlayerFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "AudioPlayerFragment.TAG";

    private static String packageName;
    private static boolean isPlaying;
    private static AudioPlayerFragment audioPlayerFragment;
    private AudioPlayerCallback activity;

    public interface AudioPlayerCallback {
        public void playSong();
        public void pauseSong();
        public void stopSong();
        public void previousSong();
        public void nextSong();
    }

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

        // Set onClickListeners
        ImageButton playButton = (ImageButton) view.findViewById(R.id.playButton);
        playButton.setOnClickListener(this);

        ImageButton stopButton = (ImageButton) view.findViewById(R.id.stopButton);
        stopButton.setOnClickListener(this);

        ImageButton previousButton = (ImageButton) view.findViewById(R.id.previousButton);
        previousButton.setOnClickListener(this);

        ImageButton nextButton = (ImageButton) view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AudioPlayerCallback) activity;

        if (!(AudioPlayerFragment.isPlaying)) {
            this.activity.playSong();
            AudioPlayerFragment.isPlaying = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                Log.i(TAG, "Play/Pause button pressed");
                ImageButton playPause = (ImageButton) getView().findViewById(R.id.playButton);
                if (AudioPlayerFragment.isPlaying) {
                    playPause.setImageResource(R.drawable.ic_action_pause);
                    AudioPlayerFragment.isPlaying = false;
                    activity.pauseSong();
                } else {
                    playPause.setImageResource(R.drawable.ic_action_play);
                    AudioPlayerFragment.isPlaying = true;
                    activity.playSong();
                }
                break;
            case R.id.stopButton:
                Log.i(TAG, "Stop button pressed");
                activity.stopSong();
                break;
            case R.id.previousButton:
                Log.i(TAG, "Previous button pressed");
                activity.previousSong();
                break;
            case R.id.nextButton:
                Log.i(TAG, "Next button pressed");
                activity.nextSong();
                break;
            default:
                break;
        }
    }
}