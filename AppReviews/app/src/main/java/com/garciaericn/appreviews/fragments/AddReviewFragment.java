package com.garciaericn.appreviews.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
import com.garciaericn.appreviews.data.Review;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/15/14.
 */
public class AddReviewFragment extends Fragment {

    public static final String TAG = "AddReviewFragment.TAG";
    private OnFragmentInteractionListener mListener;
    private Review review;

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
            case R.id.action_save:
                saveReview();
                if (review != null) {
                    mListener.saveReview(review);
                }
                break;
            default:
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveReview() {
        Log.i(TAG, "saveReview entered");

        View view = getView();

        if (view != null) {
            TextView titleTV = (TextView) getView().findViewById(R.id.new_title);
            String titleString = titleTV.getText().toString();
            if (titleString.isEmpty()) {
                alertDialog("Please enter application title");
                return;
            }

            TextView starsTV = (TextView) getView().findViewById(R.id.new_stars);
            Integer starsInt = 0;
            try {
                starsInt = Integer.parseInt(starsTV.getText().toString());
            } catch (NumberFormatException e) {
                alertDialog("Please enter star rating \nbetween 1 and 5");
                return;
            }
            if (starsInt <= 0 || starsInt > 5) {
                alertDialog("Please enter star rating \nbetween 1 and 5");
                return;
            }

            TextView summaryTV= (TextView) getView().findViewById(R.id.new_summary);
            String summaryString = String.valueOf(summaryTV.getText());
            if (titleTV == null || summaryString.isEmpty()) {
                alertDialog("Please enter review");
                return;
            }
            review = new Review(titleString, starsInt, summaryString);
        }
    }

    private void clearAll() {
        TextView titleTV = (TextView) getView().findViewById(R.id.new_title);
        titleTV.setText("");
        TextView starsTV = (TextView) getView().findViewById(R.id.new_stars);
        starsTV.setText("");
        EditText summaryTV = (EditText) getView().findViewById(R.id.new_summary);
        summaryTV.setText("");
    }

    public void alertDialog (String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Something isn't right...")
                .setMessage(message)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    /**
     * ReviewDetailFragment
     * Interface
     * */
    public interface OnFragmentInteractionListener {
        public void saveReview(Review review);
    }
}
