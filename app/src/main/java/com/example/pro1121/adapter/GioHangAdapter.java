package com.example.pro1121.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.Donhang;
import com.example.pro1121.model.GioHang;
import com.example.pro1121.model.NguoiDung;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.Viewhoder> {


    private List<GioHang> donhangList;

    public GioHangAdapter(List<GioHang> donhangList) {
        this.donhangList = donhangList;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gio_hang,parent,false);
       return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        GioHang gioHang = donhangList.get(position);
        holder.tvTenSanPham.setText(gioHang.getTensp());
        holder.tvGiaTien.setText(gioHang.getGiasp()+ "VND");
        holder.tvSoLuong.setText(gioHang.getSoluong());
        holder.tvTongSP.setText((int) gioHang.getTongsp() + "VND");

    }

    @Override
    public int getItemCount() {
        return donhangList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView tvTenSanPham, tvGiaTien, tvSoLuong,tvTongSP;
      ImageView imgDelete;

        public Viewhoder(@NonNull View view) {
            super(view);
            tvTenSanPham= view.findViewById(R.id.tvTenSanPham);
            tvGiaTien= view.findViewById(R.id.tvGiaTien);
            tvSoLuong= view.findViewById(R.id.tvSoLuong);
            tvTongSP= view.findViewById(R.id.tvTongSP);
            imgDelete = view.findViewById(R.id.imgDelete);

        }

    }

}
