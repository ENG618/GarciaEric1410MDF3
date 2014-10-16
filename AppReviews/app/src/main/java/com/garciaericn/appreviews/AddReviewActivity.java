package com.garciaericn.appreviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.garciaericn.appreviews.data.Review;
import com.garciaericn.appreviews.fragments.AddReviewFragment;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/15/14.
 */
public class AddReviewActivity extends Activity implements AddReviewFragment.OnFragmentInteractionListener{

    private static final String TAG = "AddReviewActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        Log.i(TAG, "onCreate entered");

        if (savedInstanceState == null) {

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.add_review_container, AddReviewFragment.newInstance(), AddReviewFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_clear:
                // Handled in Fragment
                break;
            case R.id.action_save:
                // Handled in Fragment
                break;
            default:
                Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void saveReview(Review review) {
        // TODO: Bundle memory and send back to activity
        Toast.makeText(this, "New review saved!!", Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Review.REVIEW, review);

        Intent returnIntent = new Intent();
        returnIntent.putExtra(Review.BUNDLED_REVIEW, bundle);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
