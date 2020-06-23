package com.hungvuong.currencyconvert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private ArrayList<String> arrayListDonVi,arrayListTyGia;
  private ArrayAdapter adapterDonVi;
  private Spinner spnTienNguon, spnTienDich;
  private Button btnConvert;
  private ImageButton imgbtnConvert;
  private TextView txtLoi;
  private TextInputEditText txtiedtTienNguon, txtiedtTienDich;
  private int flag=0,flag1=0;
  private ProgressDialog progressDialog;
  private DecimalFormat df=new DecimalFormat("0.00000");
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    AnhXa();
    arrayListDonVi=new ArrayList<>();
    adapterDonVi=new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayListDonVi);
  
    final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
    pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        finish();
        startActivity(getIntent());
        pullToRefresh.setRefreshing(true);
      }
    });//vuốt xuống để reload

    if(checkInternetConnection()==true) {
      txtLoi.setText("");
      new ReadXML().execute("https://usd.fxexchangerate.com/rss.xml");
    }else{
      txtLoi.setText("Không có internet");
      Toast.makeText(this, "Không có internet", Toast.LENGTH_SHORT).show();
    }
    spnTienNguon.setAdapter(adapterDonVi);
    spnTienDich.setAdapter(adapterDonVi);
    
    final Animation animTranslate= AnimationUtils.loadAnimation(this,R.anim.rotate_imgbtn_convert);
    imgbtnConvert.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int viTriNguon,viTriDich;
        viTriNguon=spnTienDich.getSelectedItemPosition();
        viTriDich=spnTienNguon.getSelectedItemPosition();
        spnTienNguon.setSelection(viTriNguon);
        spnTienDich.setSelection(viTriDich);
        v.startAnimation(animTranslate);
      }
    });
    btnConvert.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(spnTienNguon.getSelectedItem()==null || spnTienDich.getSelectedItem()==null){
          Toast.makeText(MainActivity.this, "Vui lòng kiểm tra internet", Toast.LENGTH_SHORT).show();
        }else{
          int viTriDich=spnTienDich.getSelectedItemPosition();
          Double a=0.0;
          if(arrayListTyGia.size() != 0) {
            a = Double.valueOf(arrayListTyGia.get(viTriDich));
          }else{
            Toast.makeText(MainActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
          }
          if(txtiedtTienNguon.getText().toString().isEmpty() || txtiedtTienNguon.getText().toString().equals(".")){
            txtiedtTienNguon.setText("1");
          }
          Double b=Double.parseDouble(String.valueOf(txtiedtTienNguon.getText()));
          
          txtiedtTienDich.setText(df.format(a*b));
          Log.d("tyGia", String.valueOf(arrayListTyGia));
        }
      }
    });

  }
  
  private void AnhXa(){
    spnTienNguon=findViewById(R.id.spinnerTienNguon);
    spnTienDich=findViewById(R.id.spinnerTienDich);
    imgbtnConvert=findViewById(R.id.imageButtonConvert);
    txtiedtTienNguon=findViewById(R.id.textInputEditTextTienNguon);
    txtiedtTienDich=findViewById(R.id.textInputEditTextTienDich);
    btnConvert=findViewById(R.id.buttonConvert);
    txtLoi=findViewById(R.id.textViewLoi);
  }
  private boolean checkInternetConnection() {
    ConnectivityManager connManager =
            (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
    
    NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
    
    if (networkInfo == null) {
//      Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
      return false;
    }
    
    if (!networkInfo.isConnected()) {
      //Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
      return false;
    }
    
    if (!networkInfo.isAvailable()) {
      //Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
      return false;
    }
  
    return true;
  }
  
  private class ReadXML extends AsyncTask<String, Void, String >{
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog=new ProgressDialog(MainActivity.this);
      progressDialog.show();
      progressDialog.setContentView(R.layout.progress_dialog);
    }
  
    @Override
    protected String doInBackground(String... strings) {
      StringBuilder builder=new StringBuilder();
      try {
        URL url=new URL(strings[0]);
        InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String line="";
        while((line=bufferedReader.readLine())!=null){
          builder.append(line);
        }
        bufferedReader.close();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      return builder.toString();
    }
    
    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);
      progressDialog.dismiss();
      XMLDOMParser parser=new XMLDOMParser();
      Document document=parser.getDocument(s);
      NodeList nodeList=document.getElementsByTagName("item");
      String mota,tyGia,tieude;
      arrayListTyGia=new ArrayList<>();
      for(int i=0;i<nodeList.getLength();i++){
        Element element= (Element) nodeList.item(i);
        tieude=parser.getValue(element,"title");
        mota=parser.getValue(element,"description");// ví dụ: 1 Vietnam Dong = 6.0E-5 Australian Dollar
  
        int doDaiTieuDe=tieude.length();
        String loaiTien=tieude.substring(doDaiTieuDe-4,doDaiTieuDe-1);
        if(flag==0) {
          arrayListDonVi.add(loaiTien);
        }
        String [] tachMoTa=mota.split("=");//sau khi tách được 1 mảng ["1 Vietnam Dong ", " 6.0E-5 Australian Dollar"]
        tyGia=tachMoTa[1].trim().split(" ")[0];// lấy phần tử vị trí thứ 1 của mảng và tách theo dấu cách lấy vị trí đầu tiên là 6.0E-5
        arrayListTyGia.add(tyGia);
      }//for
      flag=1;
      if(flag1==0){
        spnTienNguon.setSelection(adapterDonVi.getPosition("USD"));
        spnTienDich.setSelection(adapterDonVi.getPosition("VND"));
      }
      flag1=1;
      adapterDonVi.notifyDataSetChanged();
      
      spnTienNguon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          if(checkInternetConnection()==true) {
            txtLoi.setText("");
            new ReadXML().execute("https://" + spnTienNguon.getSelectedItem().toString() + ".fxexchangerate.com/rss.xml");
          }else {
            arrayListTyGia.clear();
            txtLoi.setText("Không có internet");
            Toast.makeText(MainActivity.this, "Không có internet", Toast.LENGTH_SHORT).show();
          }
        }
    
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
      });//setOnItemClick
    }//onPostExecuted
  }//AsyncTask
  
}
