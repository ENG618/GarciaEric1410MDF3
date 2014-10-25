package com.garciaericn.photolocal.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.garciaericn.photolocal.R;
import com.garciaericn.photolocal.data.Pin;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/22/14.
 */
public class PinFragment extends Fragment {

    public static final String TAG = "com.garciaericn.photolocal.fragments.PinFragment.TAG";
    private Pin pin;

    public PinFragment() {

    }

    public static PinFragment getInstance(Pin pin) {
        PinFragment fragment = new PinFragment();
        Bundle b = new Bundle();
        b.putSerializable(Pin.PIN, pin);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b != null && b.containsKey(Pin.PIN)) {
            pin = (Pin) b.getSerializable(Pin.PIN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pin, container, false);

        if (pin != null) {
            TextView titleTV = (TextView) view.findViewById(R.id.window_title);
            titleTV.setText(pin.getTitle());

            TextView descriptionTV = (TextView) view.findViewById(R.id.window_description);
            descriptionTV.setText(pin.getDescription());

            TextView latTV= (TextView) view.findViewById(R.id.lat_holder);
            latTV.setText(String.valueOf(pin.getLatitude()));

            TextView lngTV = (TextView) view.findViewById(R.id.lng_holder );
            lngTV.setText(String.valueOf(pin.getLongitude()));

            if (pin.getImageUriString() != null) {
                ImageView imageView = (ImageView) view.findViewById(R.id.window_image);
                imageView.setImageURI(Uri.parse(pin.getImageUriString()));
            }
        }

        return view;
    }
}
