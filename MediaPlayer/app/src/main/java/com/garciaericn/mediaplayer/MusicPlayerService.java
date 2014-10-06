package com.garciaericn.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */
public class MusicPlayerService extends Service{

    public class MusicPlayerBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    MusicPlayerBinder musicPlayerBinder;

    private static final int FOREGROUD_NOTIFICATION = 0x323d55;


    @Override
    public void onCreate() {
        super.onCreate();
/*
        currentSong = 0;

        songsArray = new ArrayList<Song>();

        songsArray.add(new Song("android.resource://" + getPackageName() + "/" + R.raw.blown_away, "Blown Away", "Kevin MacLeod"));
        songsArray.add(new Song("android.resource://" + getPackageName() + "/" + R.raw.carefree, "Carefree", "Kevin MacLeod"));
        songsArray.add(new Song("android.resource://" + getPackageName() + "/" + R.raw.master_of_the_feast, "Master of the Feast", "Kevin MacLeod"));
*/


//        musicPlayerBinder = new MusicPlayerBinder();
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        return Service.START_NOT_STICKY;
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
}
