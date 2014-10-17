package com.garciaericn.appreviews.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.garciaericn.appreviews.R;
import com.garciaericn.appreviews.data.DataManager;
import com.garciaericn.appreviews.data.Review;

import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/16/14.
 */
public class ReviewsWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "com.garciaericn.appsreviews.widget.ReviewsWidgetViewFactory.TAG";
    private static final int ID_CONSTANT = 0x0101010;
    private ArrayList<Review> mReviews;
    private Context mcontext;

    public ReviewsWidgetViewFactory(Context context) {
        mcontext = context;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate entered");

        DataManager mgr = DataManager.getInstance(mcontext);
        if (mgr != null) {
            // Cache reviews from disk
            mReviews = mgr.readFromDisk();
        }
    }

    @Override
    public void onDataSetChanged() {
        Log.i(TAG, "onDataSetChanged entered");
        // Heavy lifting code can go here without blocking the UI.
        // TODO: Update data in collection
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy entered");
        mReviews.clear();
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount entered");
        return mReviews.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.i(TAG, "getViewAt entered");
        // TODO: Set each view with Review at position
        Review review = mReviews.get(position);

        RemoteViews itemView = new RemoteViews(mcontext.getPackageName(), R.layout.review_item);

        itemView.setTextViewText(R.id.review_title, review.getReviewTitle());
        itemView.setTextViewText(R.id.star_rating, String.valueOf(review.getStarsEarned()) + " stars");
        itemView.setTextViewText(R.id.review_summary, review.getReviewSummary());

        Intent intent = new Intent();
//        intent.putExtra([constant from widget provider].EXTRA_ITEM, review);
        itemView.setOnClickFillInIntent(R.id.review_item, intent);
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        Log.i(TAG, "getLoadingView entered");
        return null;
    }

    @Override
    public int getViewTypeCount() {
        Log.i(TAG, "getViewTypeCount entered");
        return 1;
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "getItemId entered");
        return ID_CONSTANT + position;
    }

    @Override
    public boolean hasStableIds() {
        Log.i(TAG, "hasStableIds entered");
        return true;
    }
}
