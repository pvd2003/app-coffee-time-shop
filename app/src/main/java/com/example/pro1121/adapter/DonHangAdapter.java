package com.example.pro1121.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.Donhang;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.Viewhoder> {
    private List<Donhang> list;

    public DonHangAdapter(List<Donhang> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qly_don_hang,parent,false);
        return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Donhang donhang = list.get(position);
        holder.txtmadon.setText("Mã đơn hàng:"+donhang.getMadonhang());
        holder.txttentk.setText("Tên tài khoản:"+donhang.getTentk());
        holder.txtemail.setText("Email:"+donhang.getEmail());
        holder.txttensp.setText("Sản phẩm:"+donhang.getTensp());
        holder.txtsoluong.setText("Số lượng:"+donhang.getSoluong());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  Viewhoder extends RecyclerView.ViewHolder {
        TextView txtmadon,txtemail,txttentk,txtsoluong,txttensp, tvTongSP;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            txtmadon = itemView.findViewById(R.id.txtMaDonHang);
            txtemail = itemView.findViewById(R.id.txtEmail);
            txttentk = itemView.findViewById(R.id.txtTenTaiKhoan);
            txtsoluong = itemView.findViewById(R.id.txtSoLuong);
            txttensp = itemView.findViewById(R.id.txtTenSP);
            tvTongSP = itemView.findViewById(R.id.txtTenSP);

        }
    }
}
