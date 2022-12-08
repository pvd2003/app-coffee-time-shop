package com.example.pro1121.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.fragments.FragmentQLSanPham;
import com.example.pro1121.model.ItemClick;
import com.example.pro1121.model.NguoiDung;
import com.example.pro1121.model.Sanpham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class QuanLyTaiKhoanAdapter extends RecyclerView.Adapter<QuanLyTaiKhoanAdapter.Viewhoder> {


    private List<NguoiDung> nguoiDungList;

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qly_tai_khoan,parent,false);
       return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        NguoiDung nguoiDung = nguoiDungList.get(position);
        holder.txtTenTaiKhoan.setText(nguoiDung.getTennguoidung());
        holder.txtEmail.setText(nguoiDung.getEmail());
    }

    @Override
    public int getItemCount() {
        return nguoiDungList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView txtTenTaiKhoan, txtEmail;
      ImageView imgDelete;

        public Viewhoder(@NonNull View view) {
            super(view);
            txtTenTaiKhoan= view.findViewById(R.id.txtTenTaiKhoan);
            txtEmail= view.findViewById(R.id.txtEmail);
            imgDelete = view.findViewById(R.id.imgDelete);

        }

    }

}
