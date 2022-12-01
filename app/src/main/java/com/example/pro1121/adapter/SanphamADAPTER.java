package com.example.pro1121.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.Loaisanpham;
import com.example.pro1121.model.Sanpham;

import java.util.List;

public class SanphamADAPTER extends RecyclerView.Adapter<SanphamADAPTER.Viewhoder> {


    private List<Sanpham> mlist;

    public SanphamADAPTER(List<Sanpham> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qly_san_pham,parent,false);
       return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Sanpham sp = mlist.get(position);
     holder.txttenloai.setText(sp.getTenloai());
     holder.txtgiatien.setText(sp.getGiatien());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView txttenloai,txtgiatien;
      ImageView imsp;

        public Viewhoder(@NonNull View view) {
            super(view);
            txttenloai= view.findViewById(R.id.tvtenloaisp);
            txtgiatien = view.findViewById(R.id.tvgiatiensp);
            imsp= view.findViewById(R.id.titleSanPham);

        }
        //
    }
}
