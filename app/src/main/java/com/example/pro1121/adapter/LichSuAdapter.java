package com.example.pro1121.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.GioHang;
import com.example.pro1121.model.LichSu;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.Viewhoder> {

    private ArrayList<LichSu> mlishSu;

    public LichSuAdapter(ArrayList<LichSu> mlishSu) {
        this.mlishSu = mlishSu;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lich_su,parent,false);
       return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        LichSu lichSu = mlishSu.get(position);
        holder.tvTime.setText("Ngày: " + lichSu.getTime());
        holder.tvTrangThai.setText("Trạng thái: Đã thanh toán");
        holder.tvTong.setText("Tổng: " + lichSu.getTong());
        holder.tvemail.setText("Emai:"+lichSu.getEmail());

    }

    @Override
    public int getItemCount() {
        return mlishSu.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView tvTime, tvTrangThai, tvTong,tvemail;


        public Viewhoder(@NonNull View view) {
            super(view);
            tvTime= view.findViewById(R.id.tvTime);
            tvTrangThai= view.findViewById(R.id.tvTrangThai);
            tvTong= view.findViewById(R.id.tvTong);
            tvemail = view.findViewById(R.id.tvemail);
        }

    }

}
