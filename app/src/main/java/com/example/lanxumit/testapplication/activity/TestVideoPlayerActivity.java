package com.example.lanxumit.testapplication.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.lanxumit.testapplication.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.SimpleMainThreadMediaPlayerListener;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

public class TestVideoPlayerActivity extends AppCompatActivity {
    private static final String TAG = "TestVideoPlayerActivity";
    StandardGSYVideoPlayer videoPlayer;

    OrientationUtils orientationUtils;

    String source1 = "https://114.251.139.59:8446/alfresco/s/api/node/workspace/SpacesStore/550fb6c0-95dc-48b2-85f5-c61226a2edb6/content;cm%3Acontent?alf_ticket=TICKET_9dfba686d190bb805d62d9584959dadf736d6423";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        init();
        init2();
    }

    private void init2() {
         VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
            @Override
            public void onPlayerItemChanged(MetaData metaData) {

            }
        });
        VideoPlayerView mVideoPlayer_1 = (VideoPlayerView) findViewById(R.id.video_player_1);
        mVideoPlayer_1.addMediaPlayerListener(new SimpleMainThreadMediaPlayerListener() {
            @Override
            public void onVideoPreparedMainThread() {

            }

            @Override
            public void onVideoStoppedMainThread() {
            }

            @Override
            public void onVideoCompletionMainThread() {
            }
        });
        if (mVideoPlayerManager != null)
            mVideoPlayerManager.playNewVideo(null, mVideoPlayer_1, source1);
    }



    private void init() {
        videoPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);

//        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        videoPlayer.setUp(source1, true, "测试视频");

        //增加封面
//        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.xxx1);
//        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoPlayer.startPlayLogic();
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
