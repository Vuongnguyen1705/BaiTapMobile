package com.hungvuong.cookingebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class MainActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  ArrayList<MonAn> arrayList;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    ActionBar actionBar=getSupportActionBar();
    actionBar.setTitle(R.string.title);
    recyclerView=findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
    recyclerView.setLayoutManager(linearLayoutManager);
  
    DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
    recyclerView.addItemDecoration(dividerItemDecoration);
    
  //khởi tạo và thêm dữ liệu vào mảng
    arrayList=new ArrayList<>();
    arrayList.add(new MonAn("SESAME CHICKEN SALAD",R.drawable.sesame_chicken_salad,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/sesame-chicken-salad/","https://www.gimmesomeoven.com/wp-content/uploads/2020/05/Sesame-Chicken-Salad-Recipe-7-1100x1650.jpg"));
    arrayList.add(new MonAn("TSWEDISH CINNAMON BUNS",R.drawable.kanelbullar_swedish_cinnamon_buns_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/swedish-cinnamon-buns-kanelbullar","https://www.gimmesomeoven.com/wp-content/uploads/2020/05/Kanelbullar-Swedish-Cinnamon-Buns-Recipe-2.jpg"));
    arrayList.add(new MonAn("BAKED EGGPLANT PARMESAN",R.drawable.baked_eggplant_parmesan_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/baked-eggplant-parmesan-recipe","https://www.gimmesomeoven.com/wp-content/uploads/2015/07/Baked-Eggplant-Parmesan-Recipe-4.jpg"));
    arrayList.add(new MonAn("SOPAPILLAS",R.drawable.sopapilla_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/sopapillas","https://www.gimmesomeoven.com/wp-content/uploads/2020/05/Sopapilla-Recipe-5.jpg"));
    arrayList.add(new MonAn("ROASTED SWEET POTATO TACOS",R.drawable.roasted_sweet_potato_tacos_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/roasted-sweet-potato-tacos","https://www.gimmesomeoven.com/wp-content/uploads/2020/04/Roasted-Sweet-Potato-Tacos-Recipe-3.jpg"));
    arrayList.add(new MonAn("FATTOUSH SALAD",R.drawable.fattoush_salad_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/fattoush-salad/","https://www.gimmesomeoven.com/wp-content/uploads/2016/08/Fattoush-Salad-Recipe-6.jpg"));
  
    arrayList.add(new MonAn("MEATBALLS",R.drawable.baked_eggplant_parmesan_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/meatball-recipe","https://www.gimmesomeoven.com/wp-content/uploads/2020/04/Easy-Meatball-Recipe-3-1.jpg"));
    arrayList.add(new MonAn("SWEET POTATO ENCHILADA SOUP",R.drawable.sopapilla_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/sweet-potato-enchilada-soup","https://www.gimmesomeoven.com/wp-content/uploads/2020/04/Vegetarian-Sweet-Potato-Enchilada-Soup-Recipe-6.jpg"));
    arrayList.add(new MonAn("NO-BAKE ENERGY BITES",R.drawable.easy_no_bake_energy_bites_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/no-bake-energy-bites","https://www.gimmesomeoven.com/wp-content/uploads/2012/02/Easy-No-Bake-Energy-Bites-Recipe-4.jpg"));
    arrayList.add(new MonAn("The Juiciest Grilled Chicken Kabobs",R.drawable.fattoush_salad_recipe,R.drawable.photograph_icon,"https://www.gimmesomeoven.com/grilled-chicken-kabobs","https://www.gimmesomeoven.com/wp-content/uploads/2019/05/The-Juiciest-Chicken-Kabobs-Recipe-1-2.jpg"));
    MonAnRecyclerAdapter adapter=new MonAnRecyclerAdapter(arrayList,getApplicationContext());
    recyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));//set adapter hiển thị list có animation
  }
  public boolean checkInternetConnection() {
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
  
}
