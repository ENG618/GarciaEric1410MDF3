package com.garciaericn.appreviews.widget;

import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/16/14.
 */
public class ReviewsWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "com.garciaericn.appsreviews.widget.ReviewsWidgetViewFactory.TAG";

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate entered");
        // TODO: Get data from file, and set content
    }

    @Override
    public void onDataSetChanged() {
        Log.i(TAG, "onDataSetChanged entered");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy entered");
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount entered");
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.i(TAG, "getViewAt entered");
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
        return 0;
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "getItemId entered");
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        Log.i(TAG, "hasStableIds entered");
        return false;
    }
}
