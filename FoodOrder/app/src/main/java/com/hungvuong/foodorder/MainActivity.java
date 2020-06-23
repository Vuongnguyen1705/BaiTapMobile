package com.hungvuong.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private RadioGroup rdgSize, rdgTortilla;
  private RadioButton rdbLarge, rdbMedium, rdbCorn, rdbFlour;
  private CheckBox cbBeef, cbChicken, cbWhiteFish,cbCheese, cbSeaFood, cbRice, cbBean, cbPico, cbGuacamole, cbLBT;
  private CheckBox cbSoda, cbCerveza, cbMargarita, cbTequila;
  private Button btnOrder;
  private String message="";
  private String size="", tortilla="",fillings="Fillings: ", beverages="Beverages: ";
  private String PHONE_NUMBER="5556";
  private EditText edtHoTen, edtDiaChi;
  private static final int REQUEST_CODE_SEND_SMS=1;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ActionBar actionBar=getSupportActionBar();
    actionBar.hide();
    AnhXa();
    checkPermission();
    final AlertDialog.Builder dialog=new AlertDialog.Builder(this);
  
    btnOrder.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String hoten=edtHoTen.getText().toString().trim();
        String diachi=edtDiaChi.getText().toString().trim();
        fillings();
        if(hoten.isEmpty() || diachi.isEmpty()){
          Toast.makeText(MainActivity.this, "Vui lòng kiểm tra tên và địa chỉ", Toast.LENGTH_SHORT).show();
        }else {
          message = "I want order Bread with size: " + size + ", tortilla: " + tortilla + "\n" + fillings + "\n" + beverages
          +"\n"+hoten+"\n"+diachi;
        
          dialog.setTitle("Bạn có chắc chắn muốn đặt hàng?");
          dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
      
            }
          });
          dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              sendSms(message);
    
            }
          });
          dialog.show();
        }
//        Intent intent=new Intent();
//        intent.setAction(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("sms:"+PHONE_NUMBER));
//        intent.putExtra("sms_body",message);
//        startActivity(intent);
//        Toast.makeText(MainActivity.this, size+", "+tortilla, Toast.LENGTH_SHORT).show();
      }
    });
    
  }
  private void checkPermission(){
    if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
      this.requestPermissions(
              new String[]{Manifest.permission.SEND_SMS},
              REQUEST_CODE_SEND_SMS
      );
      return;
    }
  }
  
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode){
      case REQUEST_CODE_SEND_SMS:{
        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
          Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }else {
          Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
      }
    }
  }
  
  public void sendSms(String message)
  {
    try {
      SmsManager smsManager = SmsManager.getDefault();
      smsManager.sendTextMessage(PHONE_NUMBER,null,message , null, null);
      Toast.makeText(getApplicationContext(), "SMS sent successful", Toast.LENGTH_LONG).show();
    }
    catch(IllegalArgumentException ex)
    {
      Toast.makeText(getApplicationContext(), "Sending SMS failed", Toast.LENGTH_LONG).show();
    }
  }
  
  private void fillings(){
    // check size
    if(rdbLarge.isChecked()){
      size+=rdbLarge.getText();
    }else{
      size+=rdbMedium.getText();
    }
    // check tortilla
    if(rdbCorn.isChecked()){
      tortilla+=rdbCorn.getText();
    }else{
      tortilla+=rdbFlour.getText();
    }
    //check filling
    if(cbBeef.isChecked()){
      fillings+=cbBeef.getText()+", ";
    }
    if(cbChicken.isChecked()){
      fillings+=cbChicken.getText()+", ";
    }
    if(cbWhiteFish.isChecked()){
      fillings+=cbWhiteFish.getText()+", ";
    }
    if(cbCheese.isChecked()){
      fillings+=cbCheese.getText()+", ";
    }
    if(cbSeaFood.isChecked()){
      fillings+=cbSeaFood.getText()+", ";
    }
    if(cbRice.isChecked()){
      fillings+=cbRice.getText()+", ";
    }
    if(cbBean.isChecked()){
      fillings+=cbBean+", ";
    }
    if(cbPico.isChecked()){
      fillings+=cbPico.getText()+", ";
    }
    if(cbGuacamole.isChecked()){
      fillings+=cbGuacamole.getText()+", ";
    }
    if(cbLBT.isChecked()){
      fillings+=cbLBT.getText()+", ";
    }
    //check beverages
    if(cbSoda.isChecked()){
      beverages+=cbSoda.getText()+", ";
    }
    if(cbCerveza.isChecked()){
      beverages+=cbCerveza.getText()+", ";
    }
    if(cbMargarita.isChecked()){
      beverages+=cbMargarita.getText()+", ";
    }
    if(cbTequila.isChecked()){
      beverages+=cbTequila.getText()+", ";
    }
  }
  private void AnhXa(){
    rdgSize=(RadioGroup) findViewById(R.id.radioGroupSize);
    rdgTortilla=(RadioGroup) findViewById(R.id.radioGroupTortilla);
    rdbLarge=(RadioButton) findViewById(R.id.radioButtonLarge);
    rdbMedium=(RadioButton) findViewById(R.id.radioButtonMedium);
    rdbCorn=(RadioButton) findViewById(R.id.radioButtonCorn);
    rdbFlour=(RadioButton) findViewById(R.id.radioButtonFlour);
    cbBeef= (CheckBox) findViewById(R.id.checkBoxBeef);
    cbChicken=(CheckBox) findViewById(R.id.checkBoxChicken);
    cbWhiteFish=(CheckBox) findViewById(R.id.checkBoxWhiteFish);
    cbCheese=(CheckBox) findViewById(R.id.checkBoxCheese);
    cbSeaFood=(CheckBox) findViewById(R.id.checkBoxSeaFood);
    cbRice=(CheckBox) findViewById(R.id.checkBoxRice);
    cbBean=(CheckBox) findViewById(R.id.checkBoxBeans);
    cbPico=(CheckBox) findViewById(R.id.checkBoxPico);
    cbGuacamole=(CheckBox) findViewById(R.id.checkBoxGuacamole);
    cbLBT=(CheckBox) findViewById(R.id.checkBoxLBT);
    cbSoda=(CheckBox) findViewById(R.id.checkBoxSoda);
    cbCerveza=(CheckBox) findViewById(R.id.checkBoxCerveza);
    cbMargarita=(CheckBox) findViewById(R.id.checkBoxMargarita);
    cbTequila=(CheckBox) findViewById(R.id.checkBoxTequila);
    btnOrder= findViewById(R.id.buttonOrder);
    edtDiaChi=findViewById(R.id.editTextDiachi);
    edtHoTen=findViewById(R.id.editTextHoTen);
  }
}
