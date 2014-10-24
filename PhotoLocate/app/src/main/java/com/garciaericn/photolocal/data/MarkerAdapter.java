package com.garciaericn.photolocal.data;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.garciaericn.photolocal.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/21/14.
 */
public class MarkerAdapter implements GoogleMap.InfoWindowAdapter {

    Context mContext;
    TextView mText;
    Pin mPin;

    public MarkerAdapter() {
        if (mText == null) {
            mText = new TextView(mContext);
        }
    }

    public MarkerAdapter(Context context) {
        mContext = context;
        if (mText == null) {
            mText = new TextView(mContext);
        }
    }

    @Override
    public View getInfoContents(Marker marker) {
        mText.setText(marker.getTitle());
        return mText;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = View.inflate(mContext, R.layout.marker_window, null);

        if (marker.getSnippet() != null) {
            ImageView iconIV = (ImageView) view.findViewById(R.id.window_image);
            iconIV.setImageURI(Uri.parse(marker.getSnippet()));
        }

        TextView titleTV = (TextView) view.findViewById(R.id.window_title);
        titleTV.setText(marker.getTitle());

//        TextView descriptionTV = (TextView) view.findViewById(R.id.window_description);
//        descriptionTV.setText(marker.getSnippet());

        return view;
    }
}
