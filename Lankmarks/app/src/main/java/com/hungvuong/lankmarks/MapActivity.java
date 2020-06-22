package com.hungvuong.lankmarks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
  private GoogleMap map;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    checkInternetConnection();
    SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);
    mapFragment.getMapAsync(this);
    
  }
  
  private boolean checkInternetConnection() {
    
    ConnectivityManager connManager =
            (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
    
    
    NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
    
    if (networkInfo == null) {
      Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
      return false;
    }
    
    if (!networkInfo.isConnected()) {
      Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
      return false;
    }
    
    if (!networkInfo.isAvailable()) {
      Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
      return false;
    }
    Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
    return true;
  }
  @Override
  public void onMapReady(GoogleMap googleMap) {
    Intent intent=getIntent();
    Bundle bundle=intent.getBundleExtra("dulieu");
    double latitude=bundle.getDouble("latitude");
    double longitude=bundle.getDouble("longitude");
    String ten=bundle.getString("ten");
    map=googleMap;
    LatLng latLng=new LatLng(latitude,longitude);
    map.addMarker(new MarkerOptions().position(latLng).title(ten));
    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
  }
}