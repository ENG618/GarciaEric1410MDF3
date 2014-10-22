package com.garciaericn.photolocal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.garciaericn.photolocal.NewPinActivity;
import com.garciaericn.photolocal.data.MarkerAdapter;
import com.garciaericn.photolocal.data.Pin;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/21/14.
 */
public class PhotoMapFragment extends MapFragment
        implements
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener{

    public static final String TAG = "PhotoMapFragment.TAG";
    private static final int NEW_PIN = 1234;
    private GoogleMap googleMap;

    public PhotoMapFragment() {

    }

    public static PhotoMapFragment getInstance() {
        return new PhotoMapFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            // Get instance of map
            googleMap = getMap();

            // Add a map marker
            googleMap.addMarker(new MarkerOptions().position(new LatLng(28.590647, -81.304510)).title("MDVBS Faculty Offices"));

            // Set marker adapter
            googleMap.setInfoWindowAdapter(new MarkerAdapter(getActivity()));
            // Set listeners
            googleMap.setOnInfoWindowClickListener(this);
            googleMap.setOnMapClickListener(this);
            googleMap.setOnMapLongClickListener(this);


            // Set target, zoom, and animation
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.590647, -81.304510), 17));
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getActivity(), marker.getTitle() + " pressed", Toast.LENGTH_SHORT).show();
        // TODO: launch detail frag
    }

    @Override
    public void onMapClick(final LatLng latLng) {
        Toast.makeText(getActivity(), "Press at: " + latLng, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(final LatLng latLng) {
        // TODO: Launch AddPinFragment, to add new marker at this point.
        Intent intent = new Intent(getActivity(), NewPinActivity.class);
        intent.putExtra(Pin.LAT_LNG, latLng);

        startActivityForResult(intent, NEW_PIN);

//        new AlertDialog.Builder(getActivity())
//                .setTitle("Map Clicked")
//                .setMessage("Add new marker here?")
//                .setNegativeButton("No", null)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        googleMap.addMarker(new MarkerOptions().position(latLng).title("New Marker"));
//                    }
//                })
//                .show();
    }
}
