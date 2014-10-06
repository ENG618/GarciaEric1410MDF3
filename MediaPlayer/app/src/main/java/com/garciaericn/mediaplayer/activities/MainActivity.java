package com.garciaericn.mediaplayer.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.garciaericn.mediaplayer.MusicPlayerService;
import com.garciaericn.mediaplayer.R;
import com.garciaericn.mediaplayer.fragments.AVOptionsFragment;
import com.garciaericn.mediaplayer.fragments.AudioPlayerFragment;
import com.garciaericn.mediaplayer.fragments.VideoPlayerFragment;


public class MainActivity extends Activity
    implements AVOptionsFragment.AVOptionsFragmentCallbacks, ServiceConnection{

    private static final String TAG = "MainActivity.TAG";
    private String packageName;
    MusicPlayerService mService;
    boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        packageName = getPackageName();

        // Load AV options fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.av_options_container, AVOptionsFragment.newInstance(), AVOptionsFragment.TAG)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * AVOptionsFragmentCallbacks Methods
     */
    @Override
    public void loadAudio() {
        Log.i(TAG, "loadAudio entered");

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.player_container, AudioPlayerFragment.newInstance(packageName), AudioPlayerFragment.TAG)
                .commit();

        Intent intent = new Intent(this, MusicPlayerService.class);
        startService(intent);
    }

    @Override
    public void loadVideo() {
        Log.i(TAG, "loadVideo entered");

        Intent intent = new Intent(this, MusicPlayerService.class);
        stopService(intent);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.player_container, VideoPlayerFragment.newInstance(packageName), VideoPlayerFragment.TAG)
                .commit();
    }

    /**
    * Service Binder Methods
    */

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicPlayerService.class);
        bindService(intent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(this);

/*
            // Backup in case the service was started and not stopped.
            // Not needed if only binding was used.
            Intent intent = new Intent(this, MusicPlayerService.class);
            stopService(intent);
*/
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicPlayerService.MusicPlayerBinder binder = (MusicPlayerService.MusicPlayerBinder) service;
        mService = binder.getService();
        mBound = true;
        mService.showToastFromService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
        mBound = false;
    }
}
