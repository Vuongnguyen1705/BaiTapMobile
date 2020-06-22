package com.hungvuong.lankmarks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  private ListView lvKhuVuc;
  private ArrayList<Landmark> landmarkArrayList;
  private ArrayList<String> tenKhuVucList;
  private ArrayAdapter adapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    lvKhuVuc=findViewById(R.id.listViewKhuVuc);
    landmarkArrayList=new ArrayList<>();
    tenKhuVucList=new ArrayList<>();
    themDuLieu();
    adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, tenKhuVucList);
    lvKhuVuc.setAdapter(adapter);
    
    lvKhuVuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(MainActivity.this, landmarkArrayList.get(position).getLatitude(), Toast.LENGTH_SHORT).show();
        String ten=landmarkArrayList.get(position).getTenKhuVuc();
        showDialog(ten,position);
      }
    });
  }
  public void showDialog(final String ten, final int position){
    final Dialog dialog=new Dialog(this);
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_playhouse_square);
    final Button btnMapIt=dialog.findViewById(R.id.buttonMapIt);
    Button btnMoreInfo=dialog.findViewById(R.id.buttonMoreInfo);
    TextView txtTen=dialog.findViewById(R.id.textViewTen);
    txtTen.setText(ten);
    btnMapIt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this, MapActivity.class);
        Bundle bundle=new Bundle();
        bundle.putDouble("latitude",landmarkArrayList.get(position).getLatitude());
        bundle.putDouble("longitude",landmarkArrayList.get(position).getLongitude());
        bundle.putString("ten",ten);
        intent.putExtra("dulieu",bundle);
        dialog.dismiss();
        startActivity(intent);
      }
    });
    btnMoreInfo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,WebActivity.class);
        intent.putExtra("link",landmarkArrayList.get(position).getLinkWeb());
        dialog.dismiss();
        startActivity(intent);
      }
    });
    dialog.show();
  }
  
  private void themDuLieu(){
    landmarkArrayList.add(new Landmark(10.7599171,106.6822583,"https://sgu.edu.vn/","Đại học Sài Gòn"));
    landmarkArrayList.add(new Landmark(10.7610, 106.6826,"https://www.hcmue.edu.vn/","Đại học Sư Phạm TPHCM"));
    landmarkArrayList.add(new Landmark(10.7632, 106.6823,"https://web.hcmus.edu.vn/","Đại học Khoa Học Tự Nhiên"));
    landmarkArrayList.add(new Landmark(10.7544, 106.6633,"https://ump.edu.vn/","Đại học Y Dược TPHCM"));
    landmarkArrayList.add(new Landmark(10.7712, 106.6579,"https://www.hcmut.edu.vn/vi","Đại học Bách Khoa"));
    for(int i=0;i<landmarkArrayList.size();i++){
      tenKhuVucList.add(landmarkArrayList.get(i).getTenKhuVuc());
    }
  }
  
}