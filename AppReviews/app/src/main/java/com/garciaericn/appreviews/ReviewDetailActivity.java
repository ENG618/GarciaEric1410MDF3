package com.garciaericn.appreviews;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.garciaericn.appreviews.data.Review;
import com.garciaericn.appreviews.fragments.ReviewDetailFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/15/14.
 */
public class ReviewDetailActivity extends Activity implements ReviewDetailFragment.OnFragmentInteractionListener{


    private static final String TAG = "ReviewDetailActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
        Log.i(TAG, "onCreate entered");

        if (savedInstanceState == null) {
            // Obtain bundle from intent
            Bundle bundle = getIntent().getBundleExtra(Review.BUNDLED_REVIEW);
            Review review = (Review) bundle.getSerializable(Review.REVIEW);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.review_detail_container, ReviewDetailFragment.newInstance(review), ReviewDetailActivity.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                Toast.makeText(this, "Settings pressed", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Interface method
     * */
    @Override
    public void deleteReview(Review review) {

    }
}
