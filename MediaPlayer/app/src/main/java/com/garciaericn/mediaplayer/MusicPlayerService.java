package com.garciaericn.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/5/14.
 */
public class MusicPlayerService extends Service{


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Don't allow binding.
        return null;
    }
}
