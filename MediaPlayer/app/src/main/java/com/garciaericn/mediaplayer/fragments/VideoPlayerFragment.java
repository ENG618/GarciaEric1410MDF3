package com.garciaericn.mediaplayer.fragments;

import android.app.Fragment;
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

    private static String packageName;

    public VideoPlayerFragment() {

    }

    public static VideoPlayerFragment newInstance(String packageName) {
        VideoPlayerFragment.packageName = packageName;

        VideoPlayerFragment vpf = new VideoPlayerFragment();
        return vpf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView entered");
        //Load layout
        View view = inflater.inflate(R.layout.fragment_video_player, container, false);

        String demoVideoString = "android.resource://" + packageName + "/" + R.raw.demo;

        VideoView vv = (VideoView) view.findViewById(R.id.video_view);
        vv.setVideoURI(Uri.parse(demoVideoString));
        vv.setMediaController(new MediaController(getActivity()));
        vv.start();

        return view;
    }
}
