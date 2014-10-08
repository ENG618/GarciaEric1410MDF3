package com.garciaericn.mediaplayer.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */
public class AudioPlayerFragment extends Fragment
        implements View.OnClickListener,
        MediaPlayer.OnCompletionListener,
        ServiceConnection {

    public static final String TAG = "AudioPlayerFragment.TAG";
    private static final String SAVE_POSITION = "AudioPlayerFragment.SAVE_POSITION";

    MusicPlayerService musicPlayerService;
    boolean mBound;

    public AudioPlayerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(savedInstanceState != null && savedInstanceState.containsKey(SAVE_POSITION)) {
//            mAudioPosition = savedInstanceState.getInt(SAVE_POSITION, 0);
//        }
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

        activity.bindService(intent, (android.content.ServiceConnection) activity, Context.BIND_AUTO_CREATE);
    }

    private void setInfo(Song song){
        TextView songTV = (TextView) getView().findViewById(R.id.titleTV);
        songTV.setText(song.getSongTitle());

        TextView artistTV = (TextView) getView().findViewById(R.id.titleTV);
        artistTV.setText(song.getSongAuthor());
    }

    private void updateSeekBar() {
        // TODO: Create thread to update every second and update seekBar.
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MusicPlayerService.class);
        switch (v.getId()) {
            case R.id.playButton:
                Log.i(TAG, "Play/Pause button pressed");
                ImageButton playPause = (ImageButton) getView().findViewById(R.id.playButton);
                if (musicPlayerService.isPlaying) {
                    // Change pause button to play
                    playPause.setImageResource(R.drawable.ic_action_play);
                    // TODO: Pause from service
                    onPause();
                } else {
//                    // Create service and start service
//                    Intent intent = new Intent(getActivity(), MusicPlayerService.class);
//                    getActivity().startService(intent);

                    // Change pause button to pause
                    playPause.setImageResource(R.drawable.ic_action_pause);
                    // TODO: Call resume from service
                    onResume();
                }
                break;

            case R.id.stopButton:
                Log.i(TAG, "Stop button pressed");
                //TODO: Stop from service
                onStop();
                getActivity().stopService(intent);
                break;

            case R.id.previousButton:
                Log.i(TAG, "Previous button pressed");
                // TODO: Previous from service
                break;

            case R.id.nextButton:
                Log.i(TAG, "Next button pressed");
                // TODO: Next from service
                break;

            default:
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // TODO: Play next song
    }

    /**
     * Service Binder Methods
     */

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicPlayerService.MusicPlayerBinder binder = (MusicPlayerService.MusicPlayerBinder) service;
        musicPlayerService = binder.getService();
        mBound = true;
        musicPlayerService.showToastFromService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        musicPlayerService = null;
        mBound = false;
    }
}
