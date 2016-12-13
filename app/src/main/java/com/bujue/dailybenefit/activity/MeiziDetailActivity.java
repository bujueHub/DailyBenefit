package com.bujue.dailybenefit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.bujue.dailybenefit.R;

/**
 * @author bujue
 * @date 16/12/13
 */
public class MeiziDetailActivity extends AppCompatActivity{

    private static final String TAG = "MeiziDetailActivity";

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_detail);

        mWebView = (WebView) this.findViewById(R.id.test);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);

        initIntent(getIntent());
    }

    private void initIntent(Intent intent){
        String linkUrl = intent.getStringExtra("linkUrl");

        mWebView.loadUrl(linkUrl);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
