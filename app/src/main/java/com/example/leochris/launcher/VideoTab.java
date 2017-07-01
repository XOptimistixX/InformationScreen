package com.example.leochris.launcher;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoTab extends Fragment {

    private VideoView videoView;
    Uri selectedVideo;

    public VideoTab() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.video_fragment, container, false);
        videoView = (VideoView) v.findViewById(R.id.video_view);
//        if(selectedVideo == null) {
//            Intent pickVideo = new Intent(Intent.ACTION_PICK,
//                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(pickVideo, 1);
//        }
//        else {
//            videoView.setVideoURI(selectedVideo);
//        }
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            videoView.start();
            if (!isVisibleToUser) {
                Log.d("MyFragment", "Not visible anymore.  Stopping audio.");
                // TODO stop audio playback
                videoView.pause();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent videoReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, videoReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    selectedVideo = videoReturnedIntent.getData();
                    videoView.setVideoURI(selectedVideo);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedVideo = videoReturnedIntent.getData();
                    videoView.setVideoURI(selectedVideo);
                }
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
