package com.example.pro1121.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.Loaisanpham;

import java.util.ArrayList;
import java.util.List;

public class ThemphanloaiADAPTER extends RecyclerView.Adapter<ThemphanloaiADAPTER.Viewhoder> {


    private List<Loaisanpham> mlist;

    public ThemphanloaiADAPTER( List<Loaisanpham> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qly_loai_san_pham,parent,false);
       return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Loaisanpham lsp = mlist.get(position);
     holder.txtmaloai.setText("Mã loại: "+lsp.getMaloai());
     holder.txttenloai.setText("Tên loại:"+lsp.getTenloai());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView txtmaloai,txttenloai;
      ImageView ivedit,ivdelete;

        public Viewhoder(@NonNull View view) {
            super(view);
            txtmaloai= view.findViewById(R.id.txtMaLoai);
            txttenloai = view.findViewById(R.id.txtTenLoai);
            ivdelete= view.findViewById(R.id.ivDelete);
            ivedit= view.findViewById(R.id.ivEdit);

        }
    }
}
