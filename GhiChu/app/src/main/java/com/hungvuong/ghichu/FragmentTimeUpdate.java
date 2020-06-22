package com.hungvuong.ghichu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentTimeUpdate extends Fragment {
  TextView txtTime;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.fragment_time_update,container,false);
    txtTime= view.findViewById(R.id.textViewTime);
    return view;
  }
  public void GanNoiDung(String time){
    txtTime.setText(time);
  }
  
}
