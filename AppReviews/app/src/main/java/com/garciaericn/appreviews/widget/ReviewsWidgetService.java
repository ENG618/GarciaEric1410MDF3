package com.garciaericn.appreviews.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/16/14.
 */
public class ReviewsWidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ReviewsWidgetViewFactory(getApplicationContext());
    }
}
