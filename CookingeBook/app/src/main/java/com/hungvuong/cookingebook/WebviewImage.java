package com.hungvuong.cookingebook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebviewImage extends AppCompatActivity {
  WebView wvImage;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview_image);
    ActionBar actionBar=getSupportActionBar();
    actionBar.hide();//ẩn action bar
    wvImage=findViewById(R.id.webviewImage);
    Intent intent=getIntent();
    String url=intent.getStringExtra("linkImage");
    WebSettings webSettings=wvImage.getSettings();
    webSettings.setBuiltInZoomControls(true);//cho phép thu phóng
    webSettings.setDisplayZoomControls(false);//tắt nút thu phóng mặc định
    wvImage.loadUrl(url);
  }
}
