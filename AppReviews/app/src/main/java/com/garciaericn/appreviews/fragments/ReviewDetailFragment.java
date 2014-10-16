package com.garciaericn.appreviews.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.garciaericn.appreviews.R;
import com.garciaericn.appreviews.data.Review;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/15/14.
 */
public class ReviewDetailFragment extends Fragment {

    public static final String TAG = "ReviewDetailFragment.TAG";
    private static final String SET_ICON_AS_UP_ENABLED = "com.garciaericn.appreviews.ReviewDetailFragment.SET_ICON_AS_UP_ENABLED";

    private Review review;
    private OnFragmentInteractionListener mListener;
    private boolean setIconAsUpEnabled;

    // Required no args constructor
    public ReviewDetailFragment() {

    }

    // Static instance constructor
    public static ReviewDetailFragment newInstance(Review review, boolean setIconAsUpEnabled) {
        Log.i(TAG, "newInstance entered");
        // Create instance of fragment
        ReviewDetailFragment reviewFragment = new ReviewDetailFragment();

        // Bundle arguments
        Bundle args = new Bundle();
        args.putSerializable(Review.BUNDLED_REVIEW, review);
        args.putBoolean(SET_ICON_AS_UP_ENABLED, setIconAsUpEnabled);

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
            review = (Review) b.getSerializable(Review.BUNDLED_REVIEW);
            Log.i(TAG, "Current review: " + review.getReviewTitle());
            setIconAsUpEnabled = b.getBoolean(SET_ICON_AS_UP_ENABLED);
            if (setIconAsUpEnabled) {
                mListener.setHomeAsUp();
            }
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
        Log.i(TAG, "onCreateView entered");
        // Load layout
        View view = inflater.inflate(R.layout.fragment_review_detail, container, false);

        if (view != null && review != null) {
            // Obtain and set all fields
            TextView titleTV = (TextView) view.findViewById(R.id.detail_title);
            titleTV.setText(review.getReviewTitle());

            TextView startTV = (TextView) view.findViewById(R.id.detail_stars);
            startTV.setText(String.valueOf(review.getStarsEarned()) + " stars");

            EditText summaryTV = (EditText) view.findViewById(R.id.detail_summary);
            summaryTV.setText(review.getReviewSummary());
        }
        // Return view
        return view;
    }

    /**
     * ReviewDetailFragment
     * Interface
     * */
    public interface OnFragmentInteractionListener {
        public void deleteReview(Review review);
        public void setHomeAsUp();
    }
}
