package com.garciaericn.appreviews.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.garciaericn.appreviews.data.Review;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/15/14.
 */
public class ReviewDetailFragment extends Fragment {

    private static final String ARG_REVIEW = "com.garciaericn.appreviews.ReviewDetailFragment.REVIEW";
    public static final String TAG = "ReviewDetailFragment.TAG";

    private Review review;
    private OnFragmentInteractionListener mListener;

    // Required no args constructor
    public ReviewDetailFragment() {

    }

    // Static instance constructor
    public static ReviewDetailFragment newInstance(Review review) {
        Log.i(TAG, "newInstance entered");
        // Create instance of fragment
        ReviewDetailFragment reviewFragment = new ReviewDetailFragment();

        // Bundle arguments
        Bundle args = new Bundle();
        args.putSerializable(ARG_REVIEW, review);

        // Set args to fragment
        reviewFragment.setArguments(args);

        return reviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get arguments
        Bundle b = getArguments();

        if (b != null) {
            review = (Review) b.getSerializable(ARG_REVIEW);
            Log.i(TAG, "Current review: " + review.getReviewTitle());
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

    /**
     * ReviewDetailFragment
     * Interface
     * */
    public interface OnFragmentInteractionListener {
        public void deleteReview(Review review);
    }
}
