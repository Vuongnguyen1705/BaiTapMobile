package com.hungvuong.convert;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentHistory extends Fragment {
  SharedPreferences sharedPreferences;
  TextView txtHistory;
  Button btnClear;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_history,container,false);
    txtHistory=view.findViewById(R.id.textViewHistory);
    btnClear=view.findViewById(R.id.buttonClear);
    
    sharedPreferences=getActivity().getSharedPreferences("ketqua", Context.MODE_PRIVATE);
    txtHistory.setText(sharedPreferences.getString("history",""));
    txtHistory.setMovementMethod(new ScrollingMovementMethod());// scroll textview
    
    btnClear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sharedPreferences.edit().clear().commit();
        txtHistory.setText(sharedPreferences.getString("history",""));
      }
    });
    return view;
  }
  
  @Override
  public void onResume() {
    super.onResume();
    txtHistory.setText(sharedPreferences.getString("history",""));
  }
}
