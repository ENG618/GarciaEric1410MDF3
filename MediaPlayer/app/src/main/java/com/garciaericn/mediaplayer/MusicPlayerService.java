package com.garciaericn.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */
public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private static final String TAG = "MusicPlayerService.TAG";
    private static final int FOREGROUND_NOTIFICATION = 0x323d55;

    private MusicPlayerBinder musicPlayerBinder;
    private MediaPlayer mediaPlayer;
    private boolean mPrepared;
    private ArrayList<Song> songsArray;
    private int currentSong;
    public boolean isPlaying;
    private int mAudioPosition;


    public class MusicPlayerBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Set current song to 1st position in array
        currentSong = 0;
        // Set default position to 0
        mAudioPosition = 0;
        // Set is playing & prepared to false default
        isPlaying = mPrepared = false;

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
        if (mediaPlayer == null) {
            // Initialize media player, set audio type, set onPreparedListener
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnErrorListener(this);
            isPlaying = false;
            mPrepared = false;
        }

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

    /**
     * Media Player onPreparedListener
     * */

    @Override
    public void onPrepared(MediaPlayer mp) {
        // TODO: Set to foreground. (With Notification)
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

    // Verify if player is prepared
    public boolean isPrepared(){
        return mPrepared;
    }

    // Start media player
    public void playMedia(){
        // Prepare MP
        if (mediaPlayer != null && songsArray != null && !isPrepared()) {

            try {
                // Get instance of current song
                Song song = songsArray.get(currentSong);
                // Set data source
                mediaPlayer.setDataSource(this, Uri.parse(song.getSongString()));
                // Set notification
                setNotification(song.getSongTitle(), "Artist: " + song.getSongAuthor());
                // Set is playing to true
                isPlaying = true;
            } catch(IOException e) {
                e.printStackTrace();
                Log.i(TAG, "Media player error: " + e);

                mediaPlayer.release();
                mediaPlayer = null;
            }
            mediaPlayer.prepareAsync();
        } else { // Otherwise resume media
            resumeMedia();
        }
    }

    // Resume playing
    public void resumeMedia() {
        if(mediaPlayer != null && !mPrepared) {
            mediaPlayer.prepareAsync();
        } else if(mediaPlayer != null && mPrepared) {
//                mediaPlayer.seekTo(mAudioPosition);
            mediaPlayer.start();
            isPlaying = mPrepared = true;
        }
    }

    // Pause media
    public void pauseMedia() {
        // Pause media
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mAudioPosition = mediaPlayer.getCurrentPosition();
            mPrepared = true;
            isPlaying = false;
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
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            isPlaying = false;
            releaseMediaPlayer();
        }
    }

    // Release media player
    public void releaseMediaPlayer() {
        if(mediaPlayer != null && isPlaying) {
            mediaPlayer.stop();
            mPrepared = false;
            stopForeground(true);
            mediaPlayer.release();

        }
    }

    /**
     * OnErrorListener
     * */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.i(TAG, "There was an error in MediaPlayer: " + mp + " What: " + what + " Extra: " + extra);
        Toast.makeText(this, "Error: Please try again", Toast.LENGTH_LONG).show();
        mp.release();
        mPrepared = isPlaying = false;
        return false;
    }
 }