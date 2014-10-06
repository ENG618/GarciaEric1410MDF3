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
    public IBinder onBind(Intent intent) {
        // Don't allow binding.
        return null;
    }
}
