package com.garciaericn.mediaplayer.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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
public class AudioPlayerFragment extends Fragment implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    public static final String TAG = "AudioPlayerFragment.TAG";
    private static final String SAVE_POSITION = "AudioPlayerFragment.SAVE_POSITION";

    private static String packageName;
    private MediaPlayer mediaPlayer;
    private boolean mPrepared;
    private int mAudioPosition;
    private boolean mActivityResumed;
    private List<Song> songsArray;

    public AudioPlayerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrepared = mActivityResumed = false;
        mAudioPosition = 0;

        if(savedInstanceState != null && savedInstanceState.containsKey(SAVE_POSITION)) {
            mAudioPosition = savedInstanceState.getInt(SAVE_POSITION, 0);
        }
    }

    public static AudioPlayerFragment newInstance(String packageName) {
        AudioPlayerFragment.packageName = packageName;

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

    private void setInfo(Song song){
        TextView songTV = (TextView) getView().findViewById(R.id.titleTV);
        songTV.setText(song.getSongTitle());

        TextView artistTV = (TextView) getView().findViewById(R.id.titleTV);
        artistTV.setText(song.getSongAuthor());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Create intent and start service
        Intent intent = new Intent(getActivity(), MusicPlayerService.class);
        activity.startService(intent);

        activity.bindService(intent, (android.content.ServiceConnection) activity, Context.BIND_AUTO_CREATE);
    }
/*

    @Override
    public void onStart() {
        super.onStart();
        if(mediaPlayer == null) {

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(this);

            try {
                String audioResource = "android.resource://" + packageName + "/" + R.raw.carefree;
                mediaPlayer.setDataSource(getActivity(), Uri.parse(audioResource));
            } catch(IOException e) {
                e.printStackTrace();

                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }
*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mediaPlayer != null) {
            outState.putInt(SAVE_POSITION, mediaPlayer.getCurrentPosition());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mActivityResumed = true;
        if(mediaPlayer != null && !mPrepared) {
            mediaPlayer.prepareAsync();
        } else if(mediaPlayer != null && mPrepared) {
            mediaPlayer.seekTo(mAudioPosition);
            mediaPlayer.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mActivityResumed = false;

        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mPrepared = false;
        }
    }
/*

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
*/

    @Override
    public void onPrepared(MediaPlayer mp) {
        mPrepared = true;

        if(mediaPlayer != null && mActivityResumed) {
            mediaPlayer.seekTo(mAudioPosition);
            mediaPlayer.start();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MusicPlayerService.class);
        switch (v.getId()) {
            case R.id.playButton:
                Log.i(TAG, "Play/Pause button pressed");
                ImageButton playPause = (ImageButton) getView().findViewById(R.id.playButton);
                if (MusicPlayerService.isPlaying) {
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
}
