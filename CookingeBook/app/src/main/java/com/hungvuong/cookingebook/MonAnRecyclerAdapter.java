package com.hungvuong.cookingebook;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonAnRecyclerAdapter extends RecyclerView.Adapter<MonAnRecyclerAdapter.ViewHolder>{
  ArrayList<MonAn> monAnArrayList;
  Context context;
  MainActivity mainActivity;
  public MonAnRecyclerAdapter(ArrayList<MonAn> monAnArrayList, Context context) {
    this.monAnArrayList = monAnArrayList;
    this.context = context;
  }
  public boolean checkInternetConnection() {
    ConnectivityManager connManager =
            (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    
    NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
    
    if (networkInfo == null) {
//      Toast.makeText(context, "No default network is currently active", Toast.LENGTH_LONG).show();
      return false;
    }
    
    if (!networkInfo.isConnected()) {
//      Toast.makeText(context, "Network is not connected", Toast.LENGTH_LONG).show();
      return false;
    }
    
    if (!networkInfo.isAvailable()) {
//      Toast.makeText(context, "Network not available", Toast.LENGTH_LONG).show();
      return false;
    }
    return true;
  }
  
  @NonNull
  @Override
  public MonAnRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
    View itemView=layoutInflater.inflate(R.layout.dong_mon_an,parent,false);
    
    return new ViewHolder(itemView);
  }
  
  @Override
  public void onBindViewHolder(@NonNull final MonAnRecyclerAdapter.ViewHolder holder, final int position) {
    holder.txtTen.setText(monAnArrayList.get(position).getTen());
    holder.imgIcon.setImageResource(monAnArrayList.get(position).getIconHighRes());
    holder.imgHinh.setImageResource(monAnArrayList.get(position).getAvatar());
    holder.txtTen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(checkInternetConnection()==true) {
          Intent intent = new Intent(context, WebviewDetail.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          intent.putExtra("linkDetail", monAnArrayList.get(position).getLinkDetail());
          context.startActivity(intent);
        }else Toast.makeText(context, "Không có internet", Toast.LENGTH_SHORT).show();
      }
    });
    holder.imgIcon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(checkInternetConnection()==true) {
          Intent intent = new Intent(context, WebviewImage.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          intent.putExtra("linkImage", monAnArrayList.get(position).getLinkHighRes());
          context.startActivity(intent);
        }else Toast.makeText(context, "Không có internet", Toast.LENGTH_SHORT).show();
      }
    });
  }
  
  @Override
  public int getItemCount() {
    return monAnArrayList.size();
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtTen;
    ImageView imgIcon,imgHinh;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      txtTen=itemView.findViewById(R.id.textViewTen);
      imgIcon=itemView.findViewById(R.id.imageViewIcon);
      imgHinh=itemView.findViewById(R.id.imageViewAvatar);
  
    }
  }
  
}
