package com.hungvuong.lankmarks;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
  private WebView wv;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);
    ActionBar actionBar=getSupportActionBar();
    actionBar.hide();
    wv=findViewById(R.id.webView);
    
    Intent intent=getIntent();
    String url=intent.getStringExtra("link");
  
    WebSettings webSettings=wv.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setBuiltInZoomControls(true);
    wv.setWebViewClient(new WebViewClient());
    wv.loadUrl(url);
  }
}