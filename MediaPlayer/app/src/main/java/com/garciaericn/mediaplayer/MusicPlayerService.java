package com.garciaericn.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */
public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener {

    public boolean isPlaying;

    private MediaPlayer mediaPlayer;
    private int currentSong;
    private ArrayList<Song> songsArray;
    private boolean mPrepared;
    private boolean mActivityResumed;
    private int mAudioPosition;


    public class MusicPlayerBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    MusicPlayerBinder musicPlayerBinder;

    private static final int FOREGROUND_NOTIFICATION = 0x323d55;

    @Override
    public void onCreate() {
        super.onCreate();

        // Set current song to 1st position in array
        currentSong = 0;

        // Verify if songs array is already populated
        if (songsArray == null) {
            // Initialize array
            songsArray = new ArrayList<Song>();

            // Add songs to array via Song object
            songsArray.add(new Song("android.resource://" + getPackageName() + "/" + R.raw.blown_away, "Blown Away", "Kevin MacLeod"));
            songsArray.add(new Song("android.resource://" + getPackageName() + "/" + R.raw.carefree, "Carefree", "Kevin MacLeod"));
            songsArray.add(new Song("android.resource://" + getPackageName() + "/" + R.raw.master_of_the_feast, "Master of the Feast", "Kevin MacLeod"));
        }

        // Create and cache binder
        musicPlayerBinder = new MusicPlayerBinder();

        // Show toast the service was created
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        return Service.START_STICKY;
    }

    private void setNotification(String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_action_play)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(false)
                .setOngoing(true);

        startForeground(FOREGROUND_NOTIFICATION, builder.build());
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Service Bound", Toast.LENGTH_SHORT).show();
        return musicPlayerBinder;
    }

    public void showToastFromService() {
        Toast.makeText(this, "Toast from service", Toast.LENGTH_SHORT).show();
    }

    /**
     * Media Player onPreparedListener
     * */

    @Override
    public void onPrepared(MediaPlayer mp) {
        // TODO: Set to foreground. (With Notification)
        // TODO: Start playing.
        mPrepared = true;

        if(mediaPlayer != null) {
            mediaPlayer.seekTo(mAudioPosition);
            mediaPlayer.start();
        }
    }

    /**
     * Player Methods
     * */

    // Verify is media is currently playing
    public boolean isPlaying() {
        return isPlaying;
    }

    // Start media player
    public void playMedia(){
        // TODO: Prepare MP
        if (mediaPlayer != null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(this);

            try {
                // TODO: pull audio from array
                String audioResource = "android.resource://" + getPackageName() + "/" + R.raw.carefree;
                mediaPlayer.setDataSource(this, Uri.parse(audioResource));
            } catch(IOException e) {
                e.printStackTrace();

                mediaPlayer.release();
                mediaPlayer = null;
            }
        } else {
            // TODO: Resume playing
            mActivityResumed = true;
            if(mediaPlayer != null && !mPrepared) {
                mediaPlayer.prepareAsync();
            } else if(mediaPlayer != null && mPrepared) {
//                mediaPlayer.seekTo(mAudioPosition);
                mediaPlayer.start();
            }
        }
    }

    // Pause media
    public void pauseMedia() {
        // TODO: Pause media
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    //Next song
    public void nextSong() {
        // Increment to next song
        currentSong ++;
        // TODO: Next song start play
        // Reset, set data, prepare Async
    }

    // Previous song
    public void previousSong() {
        // Decrement to previous song
        currentSong --;
        // TODO: Start player
        // Reset, set data, prepare Async
    }

    // Stop Media
    public void stopMedia() {
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mPrepared = false;
        }
    }

    // Release media player
    public void releaseMediaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    // method if prepared

    public void test() {
        Toast.makeText(this, "test from service", Toast.LENGTH_SHORT).show();
    }

 }