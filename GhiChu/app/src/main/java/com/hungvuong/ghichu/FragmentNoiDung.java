package com.hungvuong.ghichu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentNoiDung extends Fragment {
  EditText edtNoiDung;
  View view;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view= inflater.inflate(R.layout.fragment_noidung,container,false);
    edtNoiDung=view.findViewById(R.id.edittextNoiDung);
    return view;
  }
  
  public void GanNoiDung(String noidung){
    edtNoiDung.setText(noidung);
  }
  public String getNoiDung(){
    return edtNoiDung.getText().toString();
  }
}
