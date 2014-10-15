package com.garciaericn.appreviews;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.garciaericn.appreviews.data.Review;
import com.garciaericn.appreviews.fragments.ReviewListFragment;

import java.util.ArrayList;


public class MainActivity extends Activity implements ReviewListFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity.TAG";
    private ArrayList<Review> reviewArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate entered");

        loadDefaultData();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
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
    public void onItemSelected(Review review) {
        Toast.makeText(this, review.getReviewTitle() + " was selected", Toast.LENGTH_SHORT).show();
    }
}