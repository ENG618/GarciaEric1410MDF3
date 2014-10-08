package com.garciaericn.mediaplayer.fragments;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.garciaericn.mediaplayer.R;
import com.garciaericn.mediaplayer.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */
public class AudioPlayerFragment extends Fragment implements View.OnClickListener, MediaPlayer.OnPreparedListener {

    public static final String TAG = "AudioPlayerFragment.TAG";
    private static final String SAVE_POSITION = "AudioPlayerFragment.SAVE_POSITION";

    private static String packageName;
    private static boolean isPlaying;
    private static AudioPlayerFragment audioPlayerFragment;
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

        songsArray = new ArrayList<Song>();

        songsArray.add(new Song("android.resource://" + packageName + "/" + R.raw.blown_away, "Blown Away", "Kevin MacLeod"));
        songsArray.add(new Song("android.resource://" + packageName + "/" + R.raw.carefree, "Carefree", "Kevin MacLeod"));
        songsArray.add(new Song("android.resource://" + packageName + "/" + R.raw.master_of_the_feast, "Master of the Feast", "Kevin MacLeod"));

        mPrepared = mActivityResumed = false;
        mAudioPosition = 0;

        if(savedInstanceState != null && savedInstanceState.containsKey(SAVE_POSITION)) {
            mAudioPosition = savedInstanceState.getInt(SAVE_POSITION, 0);
        }
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

    private void setInfo(Song song){
        TextView songTV = (TextView) getView().findViewById(R.id.titleTV);
        songTV.setText(song.getSongTitle());

        TextView artistTV = (TextView) getView().findViewById(R.id.titleTV);
        artistTV.setText(song.getSongAuthor());
    }

/*    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AudioPlayerCallback) activity;

        if (!(AudioPlayerFragment.isPlaying)) {
            this.activity.playSong();
            AudioPlayerFragment.isPlaying = true;
        }
    }*/
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

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
        switch (v.getId()) {
            case R.id.playButton:
                Log.i(TAG, "Play/Pause button pressed");
                ImageButton playPause = (ImageButton) getView().findViewById(R.id.playButton);
                if (AudioPlayerFragment.isPlaying) {
                    playPause.setImageResource(R.drawable.ic_action_play);
                    AudioPlayerFragment.isPlaying = false;
//                    activity.pauseSong();
                    onPause();
                } else {
                    playPause.setImageResource(R.drawable.ic_action_pause);
                    AudioPlayerFragment.isPlaying = true;
//                    activity.playSong();
                    onResume();
                }
                break;
            case R.id.stopButton:
                Log.i(TAG, "Stop button pressed");
//                activity.stopSong();
                onStop();
                break;
            case R.id.previousButton:
                Log.i(TAG, "Previous button pressed");
                break;
            case R.id.nextButton:
                Log.i(TAG, "Next button pressed");
                break;
            default:
                break;
        }
    }
}
