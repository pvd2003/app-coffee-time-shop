package com.example.pro1121.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.LoaiSanPham;

import java.util.List;

public class QuanLyLoaiSanPhamAdapter extends RecyclerView.Adapter<QuanLyLoaiSanPhamAdapter.Viewhoder> {


    private List<LoaiSanPham> mlist;

    public QuanLyLoaiSanPhamAdapter(List<LoaiSanPham> mlist) {
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
        LoaiSanPham loaiSanPham = mlist.get(position);
        holder.tvMaLoai.setText(loaiSanPham.getMaloai());
        holder.tvTenLoai.setText(loaiSanPham.getTenloai());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView tvMaLoai, tvTenLoai;
      ImageView imgDelete, imgUpdate;

        public Viewhoder(@NonNull View view) {
            super(view);
            tvMaLoai= view.findViewById(R.id.tvMaLoai);
            tvTenLoai= view.findViewById(R.id.tvTenLoai);
            imgDelete = view.findViewById(R.id.imgDelete);
            imgUpdate = view.findViewById(R.id.imgUpdate);

        }

    }

}
