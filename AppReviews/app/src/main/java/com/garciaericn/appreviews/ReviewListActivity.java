package com.garciaericn.appreviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.garciaericn.appreviews.data.DataManager;
import com.garciaericn.appreviews.data.Review;
import com.garciaericn.appreviews.data.ReviewArrayAdapter;
import com.garciaericn.appreviews.fragments.ReviewListFragment;

import java.util.ArrayList;


public class ReviewListActivity extends Activity
        implements ReviewListFragment.OnFragmentInteractionListener {

    private static final String TAG = "ReviewListActivity.TAG";
    private static final int ADD_REQUEST_CODE = 1001;
    public static final String SET_ICON_AS_UP_ENABLED = "com.garciaericn.appreviews.ReviewListActivity.UPENABLED";
    public static final String BUNDLED_EXTRA = "com.garciaericn.appreviews.ReviewListActivity.BUNDLEDEXTRA";
    private ArrayList<Review> reviewArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        Log.i(TAG, "onCreate entered");

        DataManager mgr = DataManager.getInstance(this);
        if (mgr != null) {
            if (mgr.checkFile(this)){
                reviewArrayList = mgr.readFromDisk();
            }else {
                loadDefaultData();
                mgr.writeToDisk(reviewArrayList);
            }
        }
        loadList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_add:
                Bundle b = new Bundle();
                b.putBoolean(SET_ICON_AS_UP_ENABLED, true);
                Intent intent = new Intent(this, AddReviewActivity.class);
                intent.putExtra(BUNDLED_EXTRA, b);
                startActivityForResult(intent, ADD_REQUEST_CODE);
                break;
            default:
                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadList() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.list_container, ReviewListFragment.newInstance(reviewArrayList), ReviewListFragment.TAG)
                .commit();
    }

    private void loadDefaultData() {
        Log.i(TAG, "loadDefaultData entered");

        if (reviewArrayList == null) {
            reviewArrayList = new ArrayList<Review>();

            reviewArrayList.add(new Review("Clash of Clans", 5, "Great game!  Slow starting, but good once you get going"));
            reviewArrayList.add(new Review("lynda.com", 5, "Well implemented, like that it supports Chromecast"));
            reviewArrayList.add(new Review("FX Now", 3, "Okay app.  Would be a lot better if it offered Chromecast"));
            reviewArrayList.add(new Review("Cabinet Beta", 5, "Love this file manager.  The material design looks great!"));
            reviewArrayList.add(new Review("Helium", 4, "Works pretty good, thought on upload it often freezes. It could also use a little bit more filter options"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST_CODE && resultCode == RESULT_OK) {

            Bundle bundle = data.getBundleExtra(Review.BUNDLED_REVIEW);
            Review review = (Review) bundle.getSerializable(Review.REVIEW);

            if (review != null) {
                // Add new review to list
                reviewArrayList.add(review);
                // Obtain instance of DataManager
                DataManager mgr = DataManager.getInstance(this);
                if (mgr != null) {
                    // Update file saved to disk
                    mgr.writeToDisk(reviewArrayList);
                }

                // Find list fragment
                ReviewListFragment fragment = (ReviewListFragment) getFragmentManager().findFragmentByTag(ReviewListFragment.TAG);
                // Obtain list adapter
                ReviewArrayAdapter adapter = (ReviewArrayAdapter) fragment.getListAdapter();
                // Notify data set changed
                adapter.notifyDataSetChanged();

                // Notify user saved successful
                Toast.makeText(this, review.getReviewTitle() + " review saved successfully", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Save error, please try again!!", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == ADD_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "New review was canceled", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Interface methods
     * */
    @Override
    public void onItemSelected(Review review) {
        Toast.makeText(this, review.getReviewTitle() + " was selected", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ReviewDetailActivity.class);
        intent.putExtra(Review.REVIEW, review);
        intent.putExtra(ReviewDetailActivity.SET_ICON_AS_UP_ENABLED, true);
        startActivity(intent);
    }
}