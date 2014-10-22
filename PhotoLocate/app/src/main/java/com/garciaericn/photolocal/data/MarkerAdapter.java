package com.garciaericn.photolocal.data;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

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
        mText.setText(marker.getTitle() + " adapter working!!");
        return mText;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }
}
