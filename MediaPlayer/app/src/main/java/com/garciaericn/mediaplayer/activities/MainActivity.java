package com.garciaericn.mediaplayer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.garciaericn.mediaplayer.R;
import com.garciaericn.mediaplayer.fragments.AVOptionsFragment;
import com.garciaericn.mediaplayer.fragments.AudioPlayerFragment;
import com.garciaericn.mediaplayer.fragments.VideoPlayerFragment;


public class MainActivity extends Activity
    implements AVOptionsFragment.AVOptionsFragmentCallbacks{

    private static final String TAG = "MainActivity.TAG";
    private String packageName;

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

    @Override
    public void loadAudio() {
        Log.i(TAG, "loadAudio entered");

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.player_container, AudioPlayerFragment.newInstance(packageName), AudioPlayerFragment.TAG)
                .commit();
    }

    @Override
    public void loadVideo() {
        Log.i(TAG, "loadVideo entered");

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.player_container, VideoPlayerFragment.newInstance(packageName), VideoPlayerFragment.TAG)
                .commit();
    }
}
