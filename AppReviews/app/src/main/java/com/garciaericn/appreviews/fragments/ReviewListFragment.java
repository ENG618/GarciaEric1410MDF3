package com.garciaericn.appreviews.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.garciaericn.appreviews.R;
import com.garciaericn.appreviews.data.Review;
import com.garciaericn.appreviews.data.ReviewArrayAdapter;

import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/14/14.
 */
public class ReviewListFragment extends ListFragment {

    public static final String TAG = "ReviewListFragment.TAG";
    private static final String ARG_REVIEWS_ARRAY = "com.garciaericn.reviewsapp.ReviewListFragment.reviewsArrayList";

    private ArrayList<Review> reviewArrayList;
    private OnFragmentInteractionListener mListener;

    public ReviewListFragment() {

    }

    public static ReviewListFragment newInstance(ArrayList<Review> reviewArrayList) {
        Log.i(TAG, "newInstance entered");

        // Create fragment
        ReviewListFragment fragment = new ReviewListFragment();
        // Create bundle of arguments
        Bundle args = new Bundle();
        args.putSerializable(ARG_REVIEWS_ARRAY, reviewArrayList);
        // Set arguments
        fragment.setArguments(args);
        // Return fragment
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if arguments exist
        if (getArguments() != null) {
            // Pull array from args, and cache
            reviewArrayList = (ArrayList<Review>) getArguments().getSerializable(ARG_REVIEWS_ARRAY);
        }

        // Set adapter to list
        setListAdapter(new ReviewArrayAdapter(getActivity(), R.layout.review_item, reviewArrayList));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Ensure activity is implementing listener
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Reset listener to null
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Verify listener is valid
        if (mListener != null) {
            // Sent to activity
            mListener.onItemSelected(reviewArrayList.get(position));
        }
    }

    /**
     * ReviewListFragment
     * Interface
     * */

     public interface OnFragmentInteractionListener {
        public void onItemSelected(Review review);
    }

 }
