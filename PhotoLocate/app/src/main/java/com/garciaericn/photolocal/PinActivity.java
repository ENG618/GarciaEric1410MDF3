package com.garciaericn.photolocal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.garciaericn.photolocal.data.Pin;
import com.garciaericn.photolocal.fragments.PinFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/22/14.
 */
public class PinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        if (savedInstanceState == null) {
            Intent passedIntent = getIntent();
            Bundle b = passedIntent.getExtras();
            if (b != null && b.containsKey(Pin.PIN)) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pin_container, PinFragment.getInstance((Pin) b.getSerializable(Pin.PIN)), PinFragment.TAG)
                        .commit();
            }
        }
    }
}
