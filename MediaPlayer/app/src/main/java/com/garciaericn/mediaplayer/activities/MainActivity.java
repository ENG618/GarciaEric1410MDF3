package com.garciaericn.mediaplayer.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
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
    implements AVOptionsFragment.AVOptionsFragmentCallbacks,
        ServiceConnection{

    private static final String TAG = "MainActivity.TAG";
    private String packageName;
    MusicPlayerService musicPlayerService;
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
            new AlertDialog.Builder(this)
                    .setTitle("Song Licences")
                    .setMessage("Blown Away by Kevin MacLeod is licensed under CC Attribution 3.0. \n" +"http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200100 \n\n" +
                    "Carefree by Kevin MacLeod is licensed under CC Attribution 3.0. \n" + "http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1400037. \n\n" +
                    "Master of the Feast by Kevin MacLeod is licensed under CC Attribution 3.0.\n" + "http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1400019.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();

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

    /**
     * AudioPlayer Callback
     */
/*
    @Override
    public void playSong() {
        Log.i(TAG, "playSong in activity entered");
        Intent intent = new Intent(this, MusicPlayerService.class);
        bindService(intent, this, BIND_AUTO_CREATE);
    }
*/
}
