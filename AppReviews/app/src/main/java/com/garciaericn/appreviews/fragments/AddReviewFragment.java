package com.garciaericn.appreviews.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.garciaericn.appreviews.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/15/14.
 */
public class AddReviewFragment extends Fragment {

    public static final String TAG = "AddReviewFragment.TAG";

    public AddReviewFragment() {

    }

    public static AddReviewFragment newInstance(){
        return new AddReviewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");

        View view = inflater.inflate(R.layout.fragment_add_review, container, false);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_clear:
                clearAll();
                break;
            default:
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearAll() {
        TextView titleTV = (TextView) getView().findViewById(R.id.new_title);
        titleTV.setText("");
        TextView starsTV = (TextView) getView().findViewById(R.id.new_stars);
        starsTV.setText("");
        EditText summaryTV = (EditText) getView().findViewById(R.id.new_summary);
        summaryTV.setText("");
    }
}
