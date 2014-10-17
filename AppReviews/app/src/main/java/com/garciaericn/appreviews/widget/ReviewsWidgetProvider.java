package com.garciaericn.appreviews.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.garciaericn.appreviews.R;
import com.garciaericn.appreviews.ReviewDetailActivity;
import com.garciaericn.appreviews.data.Review;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/16/14.
 */
public class ReviewsWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_VIEW_DETAILS = "com.garciaericn.appreviews.ACTION_VIEW_DETAILS";
    public static final String EXTRA_ITEM = "com.garciaericn.appreviews.widget.ReviewsWidgetProvider.EXTRA_ITEM";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Iterate through all available widgets.
        for (int widgetId : appWidgetIds) {
            // Create intent for service and bundle widget id
            Intent intent = new Intent(context, ReviewsWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

            // Create widget view, set adapter, and empty view
            RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            widgetView.setRemoteAdapter(R.id.reviews_list, intent);
            widgetView.setEmptyView(R.id.reviews_list, R.id.empty);

            // Create and set pending intent template
            Intent detailsIntent = new Intent(ACTION_VIEW_DETAILS);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, detailsIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            widgetView.setPendingIntentTemplate(R.id.reviews_list, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, widgetView);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION_VIEW_DETAILS)) {
            Review review = (Review) intent.getSerializableExtra(EXTRA_ITEM);
            if (review != null) {
                Intent details = new Intent(context, ReviewDetailActivity.class);
                details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                details.putExtra(Review.REVIEW, review);
                context.startActivity(details);
            }
        }



        super.onReceive(context, intent);
    }
}
