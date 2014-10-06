package com.garciaericn.mediaplayer.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.garciaericn.mediaplayer.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/4/14.
 */
public class VideoPlayerFragment extends Fragment {

    public static final String TAG = "VideoPlayerFragment.TAG";

    private Context mContext;

    public VideoPlayerFragment() {

    }

    public VideoPlayerFragment newInstance(Context context) {
        this.mContext = context;

        VideoPlayerFragment vpf = new VideoPlayerFragment();
        return vpf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");
        //Load layout
        View view = inflater.inflate(R.layout.fragment_video_player, container, false);

        String demoVideoString = "android.resource://" + mContext.getPackageName() + "/" + R.raw.demo;

        VideoView vv = (VideoView) getView().findViewById(R.id.video_view);
        vv.setVideoURI(Uri.parse(demoVideoString));
        vv.setMediaController(new MediaController(mContext));
        vv.start();

        return view;
    }
}
