package com.hungvuong.nationinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  ListView lvNation;
  String urlNation="http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=vuongnh&style=full";
  ArrayList<String> arrayListNationName;
  ArrayList<Nation> arrayListNation;
  ArrayAdapter adapter;
  ProgressDialog progressDialog;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    lvNation=findViewById(R.id.listViewNation);
    final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
    pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        finish();
        startActivity(getIntent());
        pullToRefresh.setRefreshing(true);
      }
    });//vuốt xuống để reload
    
    arrayListNation=new ArrayList<>();
    arrayListNationName=new ArrayList<>();
    ReadJSON(urlNation);
    adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayListNationName);
    lvNation.setAdapter(adapter);
//    Log.d("aaaa",arrayListNation.toString());
    lvNation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(MainActivity.this, DetailActivity.class);
        
        Bundle bundle=new Bundle();
        bundle.putInt("danso",arrayListNation.get(position).getPopulation());
        bundle.putString("dientich",arrayListNation.get(position).getAreaInSqKm());
        bundle.putString("ten",arrayListNation.get(position).getCountryName());
        bundle.putString("flag",arrayListNation.get(position).getFlag());
        intent.putExtra("dulieu",bundle);
        startActivity(intent);
      }
    });
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
    return true;
  }
  
  private void ReadJSON(String url){
    progressDialog=new ProgressDialog(this);
    progressDialog.show();
    progressDialog.setContentView(R.layout.progress_dialog);
    RequestQueue requestQueue= Volley.newRequestQueue(this);
    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {
                  JSONArray array=response.getJSONArray("geonames");
                  for(int i=0;i<array.length();i++) {
                    JSONObject object = array.getJSONObject(i);
                    int population=object.getInt("population");
                    String areaInSqKm=object.getString("areaInSqKm");
                    String countryCode=object.getString("countryCode");
                    String countryName=object.getString("countryName");
                    String flag="https://img.geonames.org/flags/x/"+countryCode.toLowerCase()+".gif";
                    arrayListNationName.add(countryName);
                    arrayListNation.add(new Nation(population, areaInSqKm, countryCode, countryName,flag));
                    
                  }
                  adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                checkInternetConnection();
                progressDialog.dismiss();
  
              }
            });
    requestQueue.add(jsonObjectRequest);
  }
}
