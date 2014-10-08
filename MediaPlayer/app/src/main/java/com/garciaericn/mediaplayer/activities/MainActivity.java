package com.garciaericn.mediaplayer.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.garciaericn.mediaplayer.MusicPlayerService;
import com.garciaericn.mediaplayer.R;
import com.garciaericn.mediaplayer.fragments.AudioPlayerFragment;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate entered");

        // Load AV options fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.player_container, AudioPlayerFragment.newInstance(), AudioPlayerFragment.TAG)
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
}
