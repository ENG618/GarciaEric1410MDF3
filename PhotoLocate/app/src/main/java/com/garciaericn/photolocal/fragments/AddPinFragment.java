package com.garciaericn.photolocal.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.garciaericn.photolocal.R;
import com.garciaericn.photolocal.data.Pin;
import com.google.android.gms.maps.model.LatLng;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/22/14.
 */
public class AddPinFragment extends Fragment {

    public static final String TAG = "com.garciaericn.photolocal.fragments.AddPinFragment.TAG";
    private LatLng latLng;
    private OnFragmentInteractionListener mListener;

    public AddPinFragment() {

    }

    public static AddPinFragment getInstance(LatLng latLng) {
        AddPinFragment fragment = new AddPinFragment();
        Bundle b = new Bundle();
        b.putParcelable(Pin.LAT_LNG, latLng);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle b = getArguments();
        if (b != null && b.containsKey(Pin.LAT_LNG)) {
            this.latLng = b.getParcelable(Pin.LAT_LNG);
            mListener.setHomeAsUp();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Ensure activity implements OnFragmentInteractionListener
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_pin, container, false);

        TextView latTV = (TextView) view.findViewById(R.id.lat_holder);
        latTV.setText(String.valueOf(latLng.latitude));

        TextView lngTV = (TextView) view.findViewById(R.id.lng_holder);
        lngTV.setText(String.valueOf(latLng.longitude));


        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent cancelIntent = new Intent();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * AddPinFragment
     * Interface
     * */
    public interface OnFragmentInteractionListener {
        public void setHomeAsUp();
    }
}
