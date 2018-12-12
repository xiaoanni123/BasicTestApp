package com.example.lanxumit.testapplication.activity;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.lanxumit.testapplication.R;

public class TestStatusBarActivity extends AppCompatActivity {
    private static final int LOCAL_SET_VIEW_ID = R.id.statusbar_fake_status_bar_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test_staus_bar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //basic way to chang status bar
        basicWayChangeStatusBar();
    }

    /*
     *
     *
     * */
    private void basicWayChangeStatusBar() {
        //5.0及以上的设置方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //上面的代码假设当前系统API Level >= 21，因为只有满足条件的SDK版本才能找到该方法；与此同时，在设置状态栏颜色的同时，
            //API文档 告诉我们还需要同步设置WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS这个Window Flag，
            // 并且需要保证WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS这个Window Flag没有被设置。否则，不会生效。
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.turquoise));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4及以上的设置方式
            //set  translucent -->add view (height=statusBar height)-->set view color
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            View localFakeStatusView = decorView.findViewById(LOCAL_SET_VIEW_ID);
            if (localFakeStatusView != null) {
                if (localFakeStatusView.getVisibility() == View.GONE) {
                    localFakeStatusView.setVisibility(View.VISIBLE);
                }
                localFakeStatusView.setBackgroundColor(getResources().getColor(R.color.turquoise));
            }else {
                decorView.addView(createFakeStatusView());
            }
            setRootView();
        }
    }

    private void setRootView() {
        ViewGroup parent =(ViewGroup) TestStatusBarActivity.this.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }

    private View createFakeStatusView() {
        View view = new View(TestStatusBarActivity.this);
        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(getResources().getColor(R.color.turquoise));
        view.setId(LOCAL_SET_VIEW_ID);
        return null;
    }

    private int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(identifier);
    }

}
