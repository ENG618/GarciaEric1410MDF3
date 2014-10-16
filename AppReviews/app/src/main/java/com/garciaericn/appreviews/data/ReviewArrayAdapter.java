package com.garciaericn.appreviews.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.garciaericn.appreviews.R;

import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/15/14.
 */
public class ReviewArrayAdapter extends ArrayAdapter<Review> {


    private static final String TAG = "ReviewArrayAdapter.TAG";
    private List<Review> objects;
    private Context context;

    public ReviewArrayAdapter(Context context, int resource, List<Review> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView entered");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.review_item, null);

        if (view != null) {
            TextView titleTV = (TextView) view.findViewById(R.id.review_title);
            titleTV.setText(objects.get(position).getReviewTitle());

            TextView starsTV = (TextView) view.findViewById(R.id.star_rating);
            starsTV.setText(String.valueOf(objects.get(position).getStarsEarned()) + " stars");

            TextView summaryTV = (TextView) view.findViewById(R.id.review_summary);
            summaryTV.setText(objects.get(position).getReviewSummary());
        }

        return view;
    }
}
