package com.hungvuong.cookingebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebviewDetail extends AppCompatActivity {
  WebView wvDetail;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview_detail);
    wvDetail=findViewById(R.id.webviewDetail);
    ActionBar actionBar=getSupportActionBar();
    actionBar.hide();
    wvDetail.setWebViewClient(new WebViewClient());
    Intent intent=getIntent();
    String url=intent.getStringExtra("linkDetail");
    WebSettings webSettings=wvDetail.getSettings();
    webSettings.setJavaScriptEnabled(true);// cho phép thao tác các chức năng trong web
    webSettings.setBuiltInZoomControls(true);//cho phép thu phóng
    webSettings.setDisplayZoomControls(false);//tắt nút thu phóng mặc định
    wvDetail.loadUrl(url);
  }
}
