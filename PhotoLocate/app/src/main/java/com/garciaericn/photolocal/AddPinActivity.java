package com.garciaericn.photolocal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.garciaericn.photolocal.data.Pin;
import com.garciaericn.photolocal.fragments.AddPinFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/22/14.
 */
public class AddPinActivity extends Activity implements AddPinFragment.OnFragmentInteractionListener, LocationListener{

    private static final int REQUEST_ENABLE_GPS = 0x01120;
    private LatLng mLatLng;
    private LocationManager mLocationManager;

    public AddPinActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pin);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (savedInstanceState == null) {
            Intent passedIntent = getIntent();
            Bundle b = passedIntent.getExtras();
            if (b != null && b.containsKey(Pin.LAT_LNG)) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.new_pin_container, AddPinFragment.getInstance((LatLng) b.get(Pin.LAT_LNG)), AddPinFragment.TAG)
                        .commit();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeUpdates(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_pin_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent cancelIntent = new Intent();
                setResult(RESULT_CANCELED, cancelIntent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getLocation() {
        if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
            } else {
                mLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            }

        } else {
            new AlertDialog.Builder(this)
                    .setTitle("GPS Unavailable")
                    .setMessage("Please enable GPS in the system settings.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(settingsIntent, REQUEST_ENABLE_GPS);
                        }

                    })
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_GPS) {
            getLocation();
        }
    }

    /**
     * Interface Methods
     * */

     @Override
    public void setHomeAsUp() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void savePin(Pin pin) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Pin.PIN, pin);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public LatLng getCurrentLocation() {
        getLocation();
        return mLatLng;
    }

    /**
     * Location Listener Methods
     * */

    @Override
    public void onLocationChanged(Location location) {
        mLatLng = new LatLng(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
