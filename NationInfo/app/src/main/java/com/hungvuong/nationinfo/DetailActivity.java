package com.hungvuong.nationinfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
  TextView txtDanSo,txtTen,txtDienTich;
  ImageView imgFlag;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    
    ActionBar actionBar=getSupportActionBar();
    actionBar.setTitle("Thông Tin Quốc Gia");
    
    txtDanSo=findViewById(R.id.textViewDanSo);
    txtDienTich=findViewById(R.id.textViewDienTich);
    txtTen=findViewById(R.id.textViewTen);
    imgFlag=findViewById(R.id.imageViewFlag);
  
    Intent intent=getIntent();
    Bundle bundle=intent.getBundleExtra("dulieu");
    
    String dienTich=bundle.getString("dientich");
    String ten=bundle.getString("ten");
    int danSo=bundle.getInt("danso",1);
    String flag=bundle.getString("flag");

    txtTen.setText("Quốc Gia: "+ten);
    txtDienTich.setText("Diện tích: "+dienTich+" Km vuông");
    txtDanSo.setText("Dân số: "+danSo+" người");
    Glide.with(this).load(flag).into(imgFlag);
    
  }
}
