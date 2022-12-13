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
import com.example.pro1121.model.Donhang;
import com.example.pro1121.model.GioHang;
import com.example.pro1121.model.NguoiDung;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
        holder.tvGiaTien.setText(gioHang.getGiasp());
        holder.tvSoLuong.setText("Số lượng: " + gioHang.getSoluong());
        int gia = Integer.parseInt(gioHang.getGiasp());
        int soluong = Integer.parseInt(gioHang.getSoluong());
        int tong = gia * soluong;
        holder.tvTongSP.setText("Tổng: " + tong);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tvTenSanPham.getContext());
                builder.setTitle("Bạn có chắc không?");

                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                        final CollectionReference reference = firebaseFirestore.collection("giohang");
                        reference.document(gioHang.getIdgiohang())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.tvTenSanPham.getContext(), "xóa thành công!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.tvTenSanPham.getContext(), "xóa thất bại!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return donhangList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView tvTenSanPham, tvGiaTien, tvSoLuong, tvTongSP;
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
