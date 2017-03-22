package com.qinlei.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.qinlei.myapplication.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private String mVideoPath = "rtmp://live.hkstv.hk.lxdns.com/live/hks";

    private TableLayout mHudView;
    private IjkVideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHudView = (TableLayout) findViewById(R.id.hud_view);

        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        mVideoView.setHudView(mHudView);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mVideoView.setVideoURI(Uri.parse(mVideoPath));

        mVideoView.start();

    }
}
