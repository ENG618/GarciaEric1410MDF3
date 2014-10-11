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
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.garciaericn.mediaplayer.MusicPlayerService;
import com.garciaericn.mediaplayer.R;
import com.garciaericn.mediaplayer.Song;

import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */

public class AudioPlayerFragment extends Fragment
        implements View.OnClickListener,
        ServiceConnection,
        CompoundButton.OnCheckedChangeListener,
        Runnable{

    public static final String TAG = "AudioPlayerFragment.TAG";

    private MusicPlayerService musicPlayerService;
    private boolean mBound;
    private boolean isStopped;
    private boolean isPaused;
    private SeekBar seekBar;
    private Handler handler;

    public AudioPlayerFragment() {
    }

    public static AudioPlayerFragment newInstance() {
        return new AudioPlayerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler() {
            @Override
            public void close() {

            }

            @Override
            public void flush() {

            }

            @Override
            public void publish(LogRecord record) {

            }
        };


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");

        //Load layout
        View view = inflater.inflate(R.layout.fragment_audio_player, container, false);

        if (view != null) {
            // Set onClickListeners
            ImageButton playButton = (ImageButton) view.findViewById(R.id.playButton);
            playButton.setOnClickListener(this);

            ImageButton stopButton = (ImageButton) view.findViewById(R.id.stopButton);
            stopButton.setOnClickListener(this);

            ImageButton previousButton = (ImageButton) view.findViewById(R.id.previousButton);
            previousButton.setOnClickListener(this);

            ImageButton nextButton = (ImageButton) view.findViewById(R.id.nextButton);
            nextButton.setOnClickListener(this);

            Switch repeatSwitch = (Switch) view.findViewById(R.id.repeatSwitch);
            repeatSwitch.setOnCheckedChangeListener(this);

            Switch shuffleSwitch = (Switch) view.findViewById(R.id.shuffleSwitch);
            shuffleSwitch.setOnCheckedChangeListener(this);

            if (seekBar == null) {
                seekBar = (SeekBar) getView().findViewById(R.id.seekBar);
            }
            if (musicPlayerService != null)
                seekBar.setMax(musicPlayerService.getCurrentSongDuration());
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (musicPlayerService == null) {
            // Create intent and start service
            Intent intent = new Intent(getActivity(), MusicPlayerService.class);
            activity.getApplicationContext().startService(intent);

            if (!mBound) {
                activity.getApplicationContext().bindService(intent, this, Context.BIND_AUTO_CREATE);
            }
        }
        setInfo();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void updateSeekBar() {
        if (seekBar == null) {
            seekBar = (SeekBar) getView().findViewById(R.id.seekBar);
        }

        if (seekBar != null && musicPlayerService != null) {
            // TODO: Create thread to update every second and update seekBar.
            run();
        }
    }

    @Override
    public void run() {

        int currentPosition = musicPlayerService.getCurrentSongPosition();
        int duration = musicPlayerService.getCurrentSongDuration();

        while (musicPlayerService!=null && currentPosition < duration) {
            try {
                Thread.sleep(1000);
                currentPosition = musicPlayerService.getCurrentSongPosition();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            seekBar.setProgress(currentPosition);
        }

    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.shuffleSwitch : {
                if (musicPlayerService != null) {
                    musicPlayerService.setShuffle(isChecked);
                }
            }
            case R.id.repeatSwitch : {
                if (musicPlayerService != null) {
                    musicPlayerService.setRepeat(isChecked);
                }
            }
            default:break;
        }
    }

    @Override
    public void onClick(View v) {
        // Capture instance of button
        ImageButton playPause = (ImageButton) getView().findViewById(R.id.playButton);

        // Handle media player controls
        switch (v.getId()) {
            // Play-Pause song
            case R.id.playButton:
                Log.i(TAG, "Play/Pause button pressed");

                // Playing after Stop
                // Check if player does not exist
                if (musicPlayerService != null && isStopped){
                    playPause.setImageResource(R.drawable.ic_action_pause);
                    // Play from service
                    musicPlayerService.playMedia();
                    setInfo();
                    isPaused = isStopped = false;
                    updateSeekBar();
                    break;
                }

                // Play from default or from paused
                //Check if player exist & is not playing
                if(musicPlayerService != null && !musicPlayerService.isPlaying) {
                    // Change play button to pause
                    playPause.setImageResource(R.drawable.ic_action_pause);
                    // Call resume from service
                    musicPlayerService.playMedia();
                    setInfo();
                    isPaused = isStopped = false;
                    updateSeekBar();
                    break;
                }

                // Pause from playing
                // Check if player exist & is playing
                if (musicPlayerService != null && musicPlayerService.isPlaying) {
                    // Change pause button to play
                    playPause.setImageResource(R.drawable.ic_action_play);
                    // Pause from service
                    musicPlayerService.pauseMedia();
                    isPaused = true;
                    break;
                }
                break;

            // Stops playback
            case R.id.stopButton:
                Log.i(TAG, "Stop button pressed");
                if (musicPlayerService != null) {
                    // Call stopMedia from service
                    musicPlayerService.stopMedia();
                    // Change pause button to play
                    playPause.setImageResource(R.drawable.ic_action_play);
                    isStopped = true;
                }
                break;

            // Plays previous song
            case R.id.previousButton:
                Log.i(TAG, "Previous button pressed");
                musicPlayerService.previousSong();
                setInfo();
                break;

            // Plays next song
            case R.id.nextButton:
                Log.i(TAG, "Next button pressed");
                musicPlayerService.nextSong();
                setInfo();
                break;

            default:
                break;
        }
    }

    private void setInfo() {
        if (musicPlayerService != null) {
            Song song = musicPlayerService.getCurrentSong();
            if (song != null) {
                TextView songTV = (TextView) getView().findViewById(R.id.titleTV);
                songTV.setText(song.getSongTitle());

                TextView artistTV = (TextView) getView().findViewById(R.id.authorTV);
                artistTV.setText(song.getSongAuthor());

                ImageView albumView = (ImageView) getView().findViewById(R.id.albumArt);
                albumView.setImageResource(song.getAlbumArtResourse());
            }
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