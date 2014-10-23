package com.garciaericn.photolocal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.garciaericn.photolocal.data.DataManager;
import com.garciaericn.photolocal.data.Pin;
import com.garciaericn.photolocal.fragments.PhotoMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ArrayList<Pin> pins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataManager mgr = DataManager.getInstance(this);
        if (mgr != null) {
            if (mgr.checkFile(this)) {
                pins = mgr.readFromDisk();
            } else {
                loadDefaultData();
                mgr.writeToDisk(pins);
            }
        }

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.map_container, PhotoMapFragment.getInstance(pins), PhotoMapFragment.TAG)
                .commit();
    }

    private void loadDefaultData() {
        if (pins == null) {
            pins = new ArrayList<Pin>();
        }

        pins.add(new Pin(new LatLng(28.590647, -81.304510), "MDVBS Faculty Offices", "At Full Sail"));
        pins.add(new Pin(new LatLng(28.675508, -81.311846), "oec Japanese Express", "Great little Japanese restaurant.  The sushi is amazing"));
        pins.add(new Pin(new LatLng(28.657245, -81.339514), "Lo Mejor Del Mundo Cuban", "This Cuban restaurant taste like homemade goodness.  The sandwiches are extra delicious"));
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
}
