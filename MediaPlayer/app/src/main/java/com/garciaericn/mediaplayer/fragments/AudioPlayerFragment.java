package com.garciaericn.mediaplayer.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.garciaericn.mediaplayer.MusicPlayerService;
import com.garciaericn.mediaplayer.R;
import com.garciaericn.mediaplayer.Song;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */

public class AudioPlayerFragment extends Fragment
        implements View.OnClickListener,
        ServiceConnection {

    public static final String TAG = "AudioPlayerFragment.TAG";

    MusicPlayerService musicPlayerService;
    boolean mBound;

    public AudioPlayerFragment() {

    }

    public static AudioPlayerFragment newInstance() {
        return new AudioPlayerFragment();
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

        // Create intent and start service
        Intent intent = new Intent(getActivity(), MusicPlayerService.class);
        activity.startService(intent);

        if (!mBound) {
            activity.bindService(intent, this, Context.BIND_AUTO_CREATE);
        }
    }

    private void setInfo() {
        Song song = musicPlayerService.getCurrentSong();
        if (song != null) {
            TextView songTV = (TextView) getView().findViewById(R.id.titleTV);
            songTV.setText(song.getSongTitle());

            TextView artistTV = (TextView) getView().findViewById(R.id.authorTV);
            artistTV.setText(song.getSongAuthor());
        }
    }

    private void updateSeekBar() {
        // TODO: Create thread to update every second and update seekBar.
    }

    @Override
    public void onClick(View v) {
        // Create intent to start service
        Intent intent = new Intent(getActivity(), MusicPlayerService.class);
        // Capture instance of button
        ImageButton playPause = (ImageButton) getView().findViewById(R.id.playButton);

        // Handle media player controls
        switch (v.getId()) {
            case R.id.playButton:
                Log.i(TAG, "Play/Pause button pressed");
                if (musicPlayerService == null){
                    getActivity().startService(intent);
                    if (!mBound){
                        getActivity().bindService(intent, this, Context.BIND_AUTO_CREATE);
                    }
                    // Change pause button to play
                    playPause.setImageResource(R.drawable.ic_action_play);
                    // Pause from service
//                    musicPlayerService.playMedia();
                    setInfo();
                }
                // Check if player exist & is playing
                if (musicPlayerService != null && musicPlayerService.isPlaying) {
                    // Change pause button to play
                    playPause.setImageResource(R.drawable.ic_action_play);
                    // Pause from service
                    musicPlayerService.pauseMedia();
                    setInfo();
                }

                //Check if player exist & is not playing
                if(musicPlayerService != null && !musicPlayerService.isPlaying) {
                    // Change play button to pause
                    playPause.setImageResource(R.drawable.ic_action_pause);
                    // Call resume from service
                    musicPlayerService.playMedia();
                    setInfo();
                }
                break;

            case R.id.stopButton:
                Log.i(TAG, "Stop button pressed");
                // Call stopMedia from service
                musicPlayerService.stopMedia();
                // Stop service
//                getActivity().stopService(intent);
                // Set media player to null
                musicPlayerService = null;
                // Change pause button to play
                playPause.setImageResource(R.drawable.ic_action_play);
                break;

            case R.id.previousButton:
                Log.i(TAG, "Previous button pressed");
                musicPlayerService.previousSong();
                setInfo();
                break;

            case R.id.nextButton:
                Log.i(TAG, "Next button pressed");
                musicPlayerService.nextSong();
                setInfo();
                break;

            default:
                break;
        }
    }

    /**
     * Service Binder Methods
     */

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicPlayerService.MusicPlayerBinder binder = (MusicPlayerService.MusicPlayerBinder) service;
        musicPlayerService = binder.getService();
        mBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        musicPlayerService = null;
        mBound = false;
    }
}